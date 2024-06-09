package univsys.asistenciadocente.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Carrera_Materia")
public class CarreraMateriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private int nivel;
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private CarreraEntity carrera;
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;
}
