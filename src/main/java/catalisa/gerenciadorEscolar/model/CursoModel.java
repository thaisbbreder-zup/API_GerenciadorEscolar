package catalisa.gerenciadorEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_CURSO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nomeCurso;
    @Column(length = 6, nullable = false)
    private double cargaHoraria;

  /*  @OneToMany(mappedBy = "curso")
    private List<MatriculaModel> matriculas;*/
}