package marco.U5W4Project.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import marco.U5W4Project.entities.Worker;
import marco.U5W4Project.exceptions.BadRequestException;
import marco.U5W4Project.exceptions.NotFoundException;
import marco.U5W4Project.payloads.NewWorkerDTO;
import marco.U5W4Project.repositories.WorkerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class WorkerService {
    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private Cloudinary cloudinary;

    public Worker save(NewWorkerDTO body) {
        this.workerDAO.findByEmail(body.email()).ifPresent(worker -> {
            throw new BadRequestException("Email " + worker.getEmail() + "already exists");
        });
        this.workerDAO.findByUsername(body.username()).ifPresent(worker -> {
            throw new BadRequestException("Username " + worker.getUsername() + "already exists");
        });
        
        Worker newWorker = new Worker(body.username(), body.name(), body.surname(), body.email(), "https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        return workerDAO.save(newWorker);
    }

    public Worker findById(long workerId) {
        return this.workerDAO.findById(workerId).orElseThrow(() -> new NotFoundException(workerId));
    }

    public Page<Worker> getWorkersPages(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.workerDAO.findAll(pageable);
    }

    public Worker findByIdAndUpdate(long workerId, Worker updatedWorker) {
        Worker found = this.findById(workerId);
        found.setName(updatedWorker.getName());
        found.setSurname(updatedWorker.getSurname());
        found.setEmail(updatedWorker.getEmail());
        found.setAvatar("https://ui-avatars.com/api/?name=" + updatedWorker.getName() + "+" + updatedWorker.getSurname());
        return this.workerDAO.save(found);
    }

    public void findByIdAndDelete(long userId) {
        Worker found = this.findById(userId);
        this.workerDAO.delete(found);
    }

    public Worker uploadAvatar(long workerId, MultipartFile image) throws IOException {
        Worker found = this.findById(workerId);
        String url = (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        return workerDAO.save(found);
    }

}
