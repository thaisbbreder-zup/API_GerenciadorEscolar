package catalisa.gerenciadorEscolar.service;

import catalisa.gerenciadorEscolar.model.ProfessorModel;
import catalisa.gerenciadorEscolar.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //deletar
    public void deletarProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
        }
    }
}
