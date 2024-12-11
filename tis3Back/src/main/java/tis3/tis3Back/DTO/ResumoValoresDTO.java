package tis3.tis3Back.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumoValoresDTO {
    private double custo; // Custo total dos materiais usados no serviço
    private double valorTotal; // Preço do serviço
    private double lucro; // Lucro = valorTotal - custo
}
