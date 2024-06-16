package univsys.asistenciadocente.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "licencias")
public class LicenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String razon;
    @NotNull
    private LocalDate inicio;
    @NotNull
    private LocalDate fin;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
