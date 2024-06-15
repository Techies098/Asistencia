package univsys.asistenciadocente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "horarios")
public class HorarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime inicio;
    private LocalTime fin;
    private String dia;
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private AulaEntity aula;
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private GrupoEntity grupo;
}
