package tis3.tis3Back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String carYear;
    private String description;
    private String color;
    private String ownerName;
    private String ownerNumber;
    private String ownerEmail;
    private String plate;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Servico> servicos;
}
