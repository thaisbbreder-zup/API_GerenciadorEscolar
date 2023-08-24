package catalisa.gerenciadorEscolar.service;

import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.model.ProfessorModel;
import catalisa.gerenciadorEscolar.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    //cadastrar prof
    public ProfessorModel cadastrarProfessor(ProfessorModel professor) {

        return professorRepository.save(professor);
    }
    public List<ProfessorModel> listarProfessores() {
        return professorRepository.findAll();
    }

    public Optional<ProfessorModel> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    //deletar
    public void deletarProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
        }
    }
}
