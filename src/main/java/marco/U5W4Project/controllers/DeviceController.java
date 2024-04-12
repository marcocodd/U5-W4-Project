package marco.U5W4Project.controllers;

import marco.U5W4Project.entities.Device;
import marco.U5W4Project.exceptions.BadRequestException;
import marco.U5W4Project.payloads.NewDeviceDTO;
import marco.U5W4Project.payloads.NewDeviceResponseDTO;
import marco.U5W4Project.services.DeviceService;
import marco.U5W4Project.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    WorkerService workerService;

    @GetMapping
    public Page<Device> getAllWorkers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.deviceService.getDevicePages(page, size, sortBy);
    }

    @GetMapping("/{deviceId}")
    public Device getById(@PathVariable long deviceId) {
        return this.deviceService.findById(deviceId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDeviceResponseDTO saveDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewDeviceResponseDTO(this.deviceService.save(body).getId());
    }

    // non sono sicurissimo di ciò che ho provato con Patch ecc http://localhost:3001/devices/assign?deviceId=55&workerId=102
    @PatchMapping("/assign")
    public NewDeviceResponseDTO assignDeviceToWorker(@RequestParam long deviceId, @RequestParam long workerId) {
        deviceService.assignDeviceToWorker(deviceId, workerId);
        return new NewDeviceResponseDTO(deviceId);
    }

    @PutMapping("/{deviceId}")
    public Device findByIdAndUpdate(@PathVariable long deviceId, @RequestBody Device body) {

        return this.deviceService.findByIdAndUpdate(deviceId, body);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long deviceId) {
        this.deviceService.findByIdAndDelete(deviceId);
    }


}
