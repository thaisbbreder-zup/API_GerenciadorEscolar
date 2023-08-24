package catalisa.gerenciadorEscolar.service;

import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;

    //cadastra aluno
    public AlunoModel cadastrarAluno(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    public List<AlunoModel> listarAlunos() {
        return alunoRepository.findAll();
    }

    //busca por id
    public Optional<AlunoModel> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    //deletar
    public void deletarAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
        }
    }
}
