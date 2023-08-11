package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.MatriculaDTO;
import catalisa.gerenciadorEscolar.model.MatriculaModel;
import catalisa.gerenciadorEscolar.model.MatriculaModel;
import catalisa.gerenciadorEscolar.service.MatriculaService;
import catalisa.gerenciadorEscolar.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @PostMapping
    public MatriculaModel cadastrarMatricula(@RequestBody MatriculaModel matricula) {
        return matriculaService.cadastrarMatricula(matricula);
    }

//atualizar curso
    @PatchMapping(path = "/matricula/{id}")
    public ResponseEntity<MatriculaModel> atualizarCurso(@PathVariable Long id, @RequestBody MatriculaModel cursoUpdate) {
        MatriculaModel atualizarCurso = matriculaService.atualizarCurso(id, cursoUpdate);
        return ResponseEntity.ok(atualizarCurso);
    }

    @GetMapping
    public List<MatriculaDTO> listarMatriculas() {
        List<MatriculaModel> matriculas = matriculaService.listarMatriculas();
        List<MatriculaDTO> matriculasDTO = new ArrayList<>();

        for (MatriculaModel matricula : matriculas) {
            MatriculaDTO dto = new MatriculaDTO();

            dto.setDataMatricula(matricula.getDataMatricula());
            dto.setAluno(matricula.getAluno());
            dto.setCurso(matricula.getCurso());
            matriculasDTO.add(dto);
        }
        return matriculasDTO;
    }

    @DeleteMapping(path = "/matricula/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
           matriculaService.deletarMatricula(id);
            return ResponseEntity.noContent().build();
    }
}
