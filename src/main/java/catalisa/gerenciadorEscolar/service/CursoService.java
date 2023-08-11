package catalisa.gerenciadorEscolar.service;

import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    //cadastrar curso
    public CursoModel cadastrarCurso(CursoModel professor) {
        return cursoRepository.save(professor);
    }

    public List<CursoModel> listarCursos() {
        return cursoRepository.findAll();
    }

    //busca por id
    public Optional<CursoModel> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    //deletar
    public void deletarCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        }
    }
}
