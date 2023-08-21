package catalisa.gerenciadorEscolar.service;

import catalisa.gerenciadorEscolar.model.MatriculaModel;
import catalisa.gerenciadorEscolar.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//A camada de serviço é usada para manter a separação entre a lógica de negócios e as preocupações de controle
@Service
public class MatriculaService {
    @Autowired
    MatriculaRepository matriculaRepository;

    public MatriculaModel cadastrarMatricula(MatriculaModel matricula) {
        return matriculaRepository.save(matricula);
    }

    public MatriculaModel atualizarCurso(Long id, MatriculaModel atualizacao) {
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findById(id);

        if (matriculaOptional.isPresent()) {
            MatriculaModel matriculaExistente = matriculaOptional.get();

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
