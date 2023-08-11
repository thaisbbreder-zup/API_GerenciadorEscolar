package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.ProfessorDTO;
import catalisa.gerenciadorEscolar.model.ProfessorModel;
import catalisa.gerenciadorEscolar.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ProfessorModel cadastrarProfessor(@RequestBody ProfessorModel professor) {
        return professorService.cadastrarProfessor(professor);
    }

    @GetMapping
    public List<ProfessorDTO> listarProfessores() {
        List<ProfessorModel> professores = professorService.listarProfessores();
        List<ProfessorDTO> professorDTO = new ArrayList<>();

        for (ProfessorModel professor : professores) {
            ProfessorDTO dto = new ProfessorDTO();

            dto.setNome(professor.getNome());
            dto.setCurso(professor.getCurso());
            professorDTO.add(dto);
        }
        return professorDTO;
    }

    @DeleteMapping(path = "/professor/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
