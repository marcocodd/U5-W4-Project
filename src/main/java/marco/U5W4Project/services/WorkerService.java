package marco.U5W4Project.services;

import marco.U5W4Project.repositories.WorkerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    private WorkerDAO workerDAO;
}
