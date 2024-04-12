package marco.U5W4Project.controllers;

import marco.U5W4Project.entities.Worker;
import marco.U5W4Project.exceptions.BadRequestException;
import marco.U5W4Project.payloads.NewWorkerDTO;
import marco.U5W4Project.payloads.NewWorkerResponseDTO;
import marco.U5W4Project.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public Page<Worker> getAllWorkers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.workerService.getWorkersPages(page, size, sortBy);
    }

    @GetMapping("/{workerId}")
    public Worker getById(@PathVariable long workerId) {
        return this.workerService.findById(workerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewWorkerResponseDTO saveWorker(@RequestBody @Validated NewWorkerDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewWorkerResponseDTO(this.workerService.save(body).getId());
    }

    @PutMapping("/{workerId}")
    public Worker findByIdAndUpdate(@PathVariable long workerId, @RequestBody Worker body) {

        return this.workerService.findByIdAndUpdate(workerId, body);
    }

    @DeleteMapping("/workerId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long workerId) {
        this.workerService.findByIdAndDelete(workerId);
    }

    @PostMapping("/avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        return this.workerService.uploadAvatar(avatar);

    }
}

