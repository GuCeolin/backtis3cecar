package tis3.tis3Back.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumoServicoDTO {
    private Integer quantidadeServicos;
    private Double valorTotalServicos;
}
