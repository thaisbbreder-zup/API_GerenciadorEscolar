package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.response.CursoDTO;
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
    public CursoModel cadastrarCurso(@RequestBody CursoModel curso) {
        return cursoService.cadastrarCurso(curso);
    }

    @GetMapping
    public List<CursoDTO> listarCursos(){
        List<CursoModel> cursos = cursoService.listarCursos();
        List<CursoDTO> cursosDTO = new ArrayList<>();

        for (CursoModel curso : cursos){
            CursoDTO dto = new CursoDTO();

            dto.setNomeCurso(curso.getNomeCurso());
            dto.setCargaHoraria(curso.getCargaHoraria());
            cursosDTO.add(dto);
        }
        return cursosDTO;
    }

    //busca curso por id
    @GetMapping(path = "/curso/{id}")
    public Optional<CursoModel> buscarCursoPorID(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id);
    }

    @DeleteMapping(path = "/curso/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
