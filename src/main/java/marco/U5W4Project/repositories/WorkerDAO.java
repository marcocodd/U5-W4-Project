package marco.U5W4Project.repositories;

import marco.U5W4Project.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerDAO extends JpaRepository<Worker, Long> {
    
}
