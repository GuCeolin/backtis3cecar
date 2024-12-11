package tis3.tis3Back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_service")
public class Servico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToMany
    @JoinTable(name = "service_mechanic", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "mechanic_id"))
    private List<Mechanic> mechanicsWorking;
    private String clientName;
    private String clientNumber;
    private String description;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "service_id")
    private List<MaterialUsage> materialUsages;
    private boolean serviceFinish;
    private boolean servicePaid;
}

