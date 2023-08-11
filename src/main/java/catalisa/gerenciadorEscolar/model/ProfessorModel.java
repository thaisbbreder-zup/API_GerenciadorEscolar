package catalisa.gerenciadorEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_PROFESSOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(length = 2, nullable = false)
    private int idade;
    @Column(length = 50, nullable = false)
    private String curso;
    @Column(length = 10, nullable = false)
    private double salario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel cursos;
}
