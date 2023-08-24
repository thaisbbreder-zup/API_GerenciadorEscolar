package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.MatriculaDTO;
import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.model.MatriculaModel;
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
    public ResponseEntity<MatriculaModel> cadastrarMatricula(@RequestBody MatriculaModel matricula) {
        AlunoModel aluno = matricula.getAluno();
        CursoModel curso = matricula.getCurso();

        if (aluno != null && curso != null) {
            matricula.setDataMatricula("2023-08-17");
            matricula.setAluno(aluno);
            matricula.setCurso(curso);

            MatriculaModel novaMatricula = matriculaService.cadastrarMatricula(matricula);
            return ResponseEntity.ok(novaMatricula);
        } else {
            return ResponseEntity.badRequest().build();
        }
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

            if (matricula.getAluno() != null) {
                dto.setAluno(matricula.getAluno().getNome());
            }

            if (matricula.getCurso() != null) {
                dto.setCurso(matricula.getCurso().getNomeCurso());
            }

            matriculasDTO.add(dto);
        }

        return matriculasDTO;
    }


    @DeleteMapping(path = "/matricula/{id}")
    public ResponseEntity<?> deletarMatricula(@PathVariable Long id) {
        matriculaService.deletarMatricula(id);
        return ResponseEntity.ok().body("Matrícula excluída com sucesso!");
    }
}
