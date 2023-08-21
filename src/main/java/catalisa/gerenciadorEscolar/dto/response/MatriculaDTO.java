package catalisa.gerenciadorEscolar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {
    private String dataMatricula;
    private String aluno;
    private String curso;
}
