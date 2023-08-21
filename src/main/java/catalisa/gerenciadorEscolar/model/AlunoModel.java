package catalisa.gerenciadorEscolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_ALUNO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;
    @Column(length = 2, nullable = false)
    private int idade;
    @Column(length = 50, nullable = false)
    private String email;

    // um aluno pode ter várias matrículas
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<MatriculaModel> matriculas;
}
