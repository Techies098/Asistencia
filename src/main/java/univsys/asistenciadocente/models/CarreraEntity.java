package univsys.asistenciadocente.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "carreras")
public class CarreraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String sigla;
    @OneToMany(mappedBy = "carrera")
    private List<CarreraMateriaEntity> materias;
    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private FacultadEntity facultad;

}
