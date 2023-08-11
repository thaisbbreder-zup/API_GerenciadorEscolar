package catalisa.gerenciadorEscolar.service;

import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.model.MatriculaModel;
import catalisa.gerenciadorEscolar.model.MatriculaModel;
import catalisa.gerenciadorEscolar.repository.CursoRepository;
import catalisa.gerenciadorEscolar.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    @Autowired
    MatriculaRepository matriculaRepository;

    public MatriculaModel cadastrarMatricula(MatriculaModel matricula) {
        return matriculaRepository.save(matricula);
    }

    public MatriculaModel atualizarCurso(Long id, MatriculaModel atualizacao) {
        Optional<MatriculaModel> matricula = matriculaRepository.findById(id);
        if (matricula.isPresent()) {
            MatriculaModel matriculaExistente = matricula.get();

            if (atualizacao.getCurso() != null) {
                matriculaExistente.setCurso(atualizacao.getCurso());
            }
            return matriculaRepository.save(matriculaExistente);
        } else {
            return null;
        }
    }

    public List<MatriculaModel> listarMatriculas() {
        return matriculaRepository.findAll();
    }

    //busca por id
    public Optional<MatriculaModel> buscarMatriculaPorId(Long id) {
        return matriculaRepository.findById(id);
    }

    //deletar
    public void deletarMatricula(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
        }
    }
}
