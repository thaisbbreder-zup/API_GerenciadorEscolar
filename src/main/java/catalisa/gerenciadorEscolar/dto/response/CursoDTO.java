package catalisa.gerenciadorEscolar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    private String nomeCurso;
    private double cargaHoraria;
}
