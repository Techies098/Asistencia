package univsys.asistenciadocente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "grupos")
public class GrupoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String periodo;
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
