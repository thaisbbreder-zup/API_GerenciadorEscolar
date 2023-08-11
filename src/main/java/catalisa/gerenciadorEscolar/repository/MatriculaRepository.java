package catalisa.gerenciadorEscolar.repository;

import catalisa.gerenciadorEscolar.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, Long> {
    // Consultas personalizadas podem ser definidas aqui
}
