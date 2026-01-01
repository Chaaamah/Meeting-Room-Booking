package ma.chaima.msusers.Repositories;

import ma.chaima.msusers.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
}
