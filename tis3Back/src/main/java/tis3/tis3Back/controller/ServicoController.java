package tis3.tis3Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tis3.tis3Back.DTO.ResumoMaterialDTO;
import tis3.tis3Back.DTO.ResumoServicoDTO;
import tis3.tis3Back.DTO.ResumoValoresDTO;
import tis3.tis3Back.model.Servico;
import tis3.tis3Back.service.ServicoService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/service")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // Método para retornar todos os serviços
    @GetMapping
    public ResponseEntity<List<Servico>> getAllServices() {
        List<Servico> list = servicoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método para buscar um serviço específico pelo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        Servico obj = servicoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Servico> insert(@RequestBody Servico obj) {
        obj = servicoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    // Método para deletar um serviço por ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Método para atualizar um serviço
    @PutMapping(value = "/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico obj) {
        obj = servicoService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/resumoServico")
    public ResponseEntity<ResumoServicoDTO> getResumoServicos(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        ResumoServicoDTO resumo = servicoService.getResumoServicos(startDate, endDate);
        return ResponseEntity.ok().body(resumo);
    }

    @GetMapping("/resumoMateriais")
    public ResponseEntity<ResumoMaterialDTO> getResumoMateriais(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        ResumoMaterialDTO resumo = servicoService.getResumoMateriais(startDate, endDate);
        return ResponseEntity.ok().body(resumo);
    }

    @GetMapping("/resumoValores")
    public ResponseEntity<List<ResumoValoresDTO>> getResumoValores(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<ResumoValoresDTO> resumo = servicoService.getResumoValores(startDate, endDate);
        return ResponseEntity.ok().body(resumo);
    }
}
