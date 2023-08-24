package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.AlunoDTO;
import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.repository.MatriculaRepository;
import catalisa.gerenciadorEscolar.service.AlunoService;
import catalisa.gerenciadorEscolar.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    CursoService cursoService;
    @Autowired
    MatriculaRepository matriculaRepository;

    @PostMapping
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoModel alunoModel) {
        AlunoModel novoAluno = alunoService.cadastrarAluno(alunoModel);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome(novoAluno.getNome());
        alunoDTO.setEmail(novoAluno.getEmail());

        return new ResponseEntity<>(alunoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        List<AlunoModel> alunos = alunoService.listarAlunos();
        List<AlunoDTO> alunosDTO = new ArrayList<>();

        for (AlunoModel aluno : alunos) {
            AlunoDTO dto = new AlunoDTO();
            dto.setNome(aluno.getNome());
            dto.setEmail(aluno.getEmail());
            alunosDTO.add(dto);
        }
        return ResponseEntity.ok(alunosDTO);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<AlunoModel> buscarAlunoPorId(@PathVariable Long id) {
        Optional<AlunoModel> aluno = alunoService.buscarAlunoPorId(id);

        return aluno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarAlunoPorID(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.ok().body("Aluno(a) excluído(a) com sucesso!");
    }
}
