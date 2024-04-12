package marco.U5W4Project.repositories;

import marco.U5W4Project.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerDAO extends JpaRepository<Worker, Long> {
    Optional<Worker> findByEmail(String email);

    Optional<Worker> findByUsername(String username);
}
