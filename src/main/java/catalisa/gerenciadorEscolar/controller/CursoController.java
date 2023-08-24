package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.CursoDTO;
import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTO> cadastrarCurso(@RequestBody CursoModel curso) {
        CursoModel novoCurso = cursoService.cadastrarCurso(curso);

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNomeCurso(novoCurso.getNomeCurso());
        cursoDTO.setCargaHoraria(novoCurso.getCargaHoraria());

        return ResponseEntity.ok(cursoDTO);
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        List<CursoModel> cursos = cursoService.listarCursos();
        List<CursoDTO> cursosDTO = new ArrayList<>();

        for (CursoModel curso : cursos) {
            CursoDTO dto = new CursoDTO();

            dto.setNomeCurso(curso.getNomeCurso());
            dto.setCargaHoraria(curso.getCargaHoraria());
            cursosDTO.add(dto);
        }
        return ResponseEntity.ok(cursosDTO);
    }

    //busca curso por id
    @GetMapping(path = "{id}")
    public ResponseEntity<CursoModel> buscarCursoPorID(@PathVariable Long id) {
        Optional<CursoModel> curso = cursoService.buscarCursoPorId(id);

        return curso.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.ok().body("Curso excluído com sucesso!");
    }
}
