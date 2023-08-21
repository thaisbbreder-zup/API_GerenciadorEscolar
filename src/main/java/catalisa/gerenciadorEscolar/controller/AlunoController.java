package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.dto.response.AlunoDTO;
import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.repository.MatriculaRepository;
import catalisa.gerenciadorEscolar.service.AlunoService;
import catalisa.gerenciadorEscolar.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    CursoService  cursoService;
    @Autowired
    MatriculaRepository matriculaRepository;

    @PostMapping
    public AlunoModel cadastrarAluno(@RequestBody AlunoModel aluno) { //A anotação @RequestBody no Spring converte os dados enviados no corpo de uma solicitação HTTP (por exemplo, em uma requisição POST ou PUT) no formato apropriado, como JSON
        return alunoService.cadastrarAluno(aluno);
    }

    @GetMapping
    public List<AlunoDTO> listarAlunos(){
        List<AlunoModel> alunos = alunoService.listarAlunos();
        List<AlunoDTO> alunosDTO = new ArrayList<>();

        for (AlunoModel aluno : alunos){
            AlunoDTO dto = new AlunoDTO();

            dto.setNome(aluno.getNome());
            dto.setEmail(aluno.getEmail());
            alunosDTO.add(dto);
        }
        return alunosDTO;
    }

    //busca aluno por id
    @GetMapping(path = "/aluno/{id}")
    public Optional<AlunoModel> buscarAlunoPorID(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id);
    }

    @DeleteMapping(path = "/aluno/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }

}
