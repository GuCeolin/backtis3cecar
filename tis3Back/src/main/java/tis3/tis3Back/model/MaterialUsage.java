package tis3.tis3Back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_service_material_usage")
public class MaterialUsage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
    private Double priceAtServiceTime;
    private Integer quantity;

    public Double calculateTotalCost() {
        return priceAtServiceTime * quantity;
    }
}
