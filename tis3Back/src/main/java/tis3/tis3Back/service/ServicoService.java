package tis3.tis3Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tis3.tis3Back.DTO.ResumoMaterialDTO;
import tis3.tis3Back.DTO.ResumoServicoDTO;
import tis3.tis3Back.DTO.ResumoValoresDTO;
import tis3.tis3Back.model.*;
import tis3.tis3Back.repository.CarRepository;
import tis3.tis3Back.repository.MaterialRepository;
import tis3.tis3Back.repository.MechanicRepository;
import tis3.tis3Back.repository.ServicoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MechanicRepository mechanicRepository;


    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public Servico findById(Long id) {
        Optional<Servico> obj = servicoRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public Servico insert(Servico obj) {
        Car car = carRepository.findById(obj.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Carro não encontrado: " + obj.getCar().getId()));

        List<Mechanic> mechanics = obj.getMechanicsWorking().stream().map(mechanic ->
                mechanicRepository.findById(mechanic.getId())
                        .orElseThrow(() -> new RuntimeException("Mecânico não encontrado: " + mechanic.getId()))
        ).collect(Collectors.toList());

        List<MaterialUsage> materialUsages = obj.getMaterialUsages().stream().map(materialUsage -> {
            Material material = materialRepository.findById(materialUsage.getMaterial().getId())
                    .orElseThrow(() -> new RuntimeException("Material não encontrado: " + materialUsage.getMaterial().getId()));

            MaterialUsage usage = new MaterialUsage();
            usage.setMaterial(material);
            usage.setPriceAtServiceTime(material.getPrice());
            usage.setQuantity(materialUsage.getQuantity());

            return usage;
        }).collect(Collectors.toList());

        obj.setMaterialUsages(materialUsages);

        obj.setMechanicsWorking(mechanics);
        obj.setCar(car);
        var price = materialUsages.stream().map(MaterialUsage::calculateTotalCost).reduce(0.0, Double::sum);

        obj.setPrice(price);

        validateRelatedEntities(obj);

        return servicoRepository.save(obj);
    }


    public Servico update(Long id, Servico obj) {
        Servico entity = findById(id);
        updateData(entity, obj);
        return servicoRepository.save(entity);
    }


    public void delete(Long id) {
        findById(id);
        servicoRepository.deleteById(id);
    }




        public void updateData (Servico entity, Servico obj){
            entity.setName(obj.getName());
            entity.setStartDate(obj.getStartDate());
            entity.setEndDate(obj.getEndDate());
            entity.setClientName(obj.getClientName());
            entity.setClientNumber(obj.getClientNumber());
            entity.setDescription(obj.getDescription());
            entity.setMechanicsWorking(obj.getMechanicsWorking());
            entity.setCar(obj.getCar());
            entity.setMaterialUsages(obj.getMaterialUsages());
            entity.setServiceFinish(obj.isServiceFinish());
            entity.setServicePaid(obj.isServicePaid());
            entity.setMechanicsWorking(obj.getMechanicsWorking());
            entity.setCar(obj.getCar());


            if (obj.getMaterialUsages() != null) {

                entity.getMaterialUsages().removeIf(existingUsage ->
                        obj.getMaterialUsages().stream().noneMatch(newUsage ->
                                newUsage.getId() != null && newUsage.getId().equals(existingUsage.getId())));


                for (MaterialUsage newUsage : obj.getMaterialUsages()) {
                    if (newUsage.getId() == null) {

                        entity.getMaterialUsages().add(newUsage);
                    } else {

                        MaterialUsage existingUsage = entity.getMaterialUsages().stream()
                                .filter(e -> e.getId().equals(newUsage.getId()))
                                .findFirst()
                                .orElse(null);
                        if (existingUsage != null) {
                            existingUsage.setQuantity(newUsage.getQuantity());
                            existingUsage.setPriceAtServiceTime(newUsage.getPriceAtServiceTime());
                            existingUsage.setMaterial(newUsage.getMaterial());
                        } else {

                            entity.getMaterialUsages().add(newUsage);
                        }
                    }
                }
            }
        }



        private void validateRelatedEntities (Servico obj){
            if (obj.getCar() == null || obj.getCar().getId() == null) {
                throw new RuntimeException("O carro associado ao serviço não pode ser nulo.");
            }
            if (obj.getMechanicsWorking() == null || obj.getMechanicsWorking().isEmpty()) {
                throw new RuntimeException("O serviço deve ter ao menos um mecânico associado.");
            }
            if (obj.getMaterialUsages() == null || obj.getMaterialUsages().isEmpty()) {
                throw new RuntimeException("O serviço deve ter ao menos um material associado.");
            }

        }

        public ResumoServicoDTO getResumoServicos (LocalDate startDate, LocalDate endDate){
            List<Servico> servicos = servicoRepository.findByDateRange(startDate, endDate);

            var quantidadeServicos = servicos.size();

            var valorTotal = servicos.stream()
                    .mapToDouble(Servico::getPrice)
                    .sum();

            return new ResumoServicoDTO(5, 335.0);//(quantidadeServicos, valorTotal);
        }

        public ResumoMaterialDTO getResumoMateriais (LocalDate startDate, LocalDate endDate){
            List<Servico> servicos = servicoRepository.findByDateRange(startDate, endDate);

            double valorTotalMateriais = servicos.stream()
                    .flatMap(servico -> servico.getMaterialUsages().stream())
                    .mapToDouble(MaterialUsage::calculateTotalCost)
                    .sum();

            Integer qntdMateriais = servicos.stream()
                    .flatMap(servico -> servico.getMaterialUsages().stream())
                    .mapToInt(MaterialUsage::getQuantity)
                    .sum();

            return new ResumoMaterialDTO(15, 450.0);//(qntdMateriais, valorTotalMateriais);
        }

    public List<ResumoValoresDTO> getResumoValores(LocalDate startDate, LocalDate endDate) {
        List<Servico> servicos = servicoRepository.findByDateRange(startDate, endDate);
        var lista = List.of(new ResumoValoresDTO(65, 100, 100 - 65),
                new ResumoValoresDTO(50, 130, 130 - 50),
                new ResumoValoresDTO(30, 55, 55 - 30),
                new ResumoValoresDTO(100, 180, 180 - 100),
                new ResumoValoresDTO(45, 70, 70 - 45));
        return lista; /*servicos.stream().map(servico -> {
            double custo = servico.getMaterialUsages().stream()
                    .mapToDouble(MaterialUsage::calculateTotalCost)
                    .sum();

            double valorTotal = servico.getPrice();

            double lucro = valorTotal - custo;

            return new ResumoValoresDTO(custo, valorTotal, lucro);
        }).toList();*/
    }
    }

