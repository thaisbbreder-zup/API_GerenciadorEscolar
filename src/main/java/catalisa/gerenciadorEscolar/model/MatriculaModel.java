package catalisa.gerenciadorEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_MATRICULA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String dataMatricula;
    @Column(length = 50, nullable = false)
    private String aluno;
    @Column(length = 50, nullable = false)
    private String curso;

/*  @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoModel alunos;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel cursos;
    */

}
