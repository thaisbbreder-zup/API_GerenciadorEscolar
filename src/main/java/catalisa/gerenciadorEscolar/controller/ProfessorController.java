package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.ProfessorDTO;
import catalisa.gerenciadorEscolar.model.ProfessorModel;
import catalisa.gerenciadorEscolar.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDTO> cadastrarProfessor(@RequestBody ProfessorModel professorModel) {
        ProfessorModel novoProfessor = professorService.cadastrarProfessor(professorModel);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setNome(novoProfessor.getNome());
        professorDTO.setCurso(novoProfessor.getCurso());

        return new ResponseEntity<>(professorDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarProfessores() {
        List<ProfessorModel> professores = professorService.listarProfessores();
        List<ProfessorDTO> professoresDTO = new ArrayList<>();

        for (ProfessorModel professor : professores) {
            ProfessorDTO dto = new ProfessorDTO();
            dto.setNome(professor.getNome());
            dto.setCurso(professor.getCurso());
            professoresDTO.add(dto);
        }
        return ResponseEntity.ok(professoresDTO);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProfessorModel> buscarProfessorPorId(@PathVariable Long id) {
        Optional<ProfessorModel> professor = professorService.buscarProfessorPorId(id);

        return professor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarProfessorPorID(@PathVariable Long id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.ok().body("Professor(a) excluído(a) com sucesso!");
    }
}
