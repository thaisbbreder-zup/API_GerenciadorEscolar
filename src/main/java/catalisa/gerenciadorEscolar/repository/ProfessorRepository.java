package catalisa.gerenciadorEscolar.repository;

import catalisa.gerenciadorEscolar.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {

}