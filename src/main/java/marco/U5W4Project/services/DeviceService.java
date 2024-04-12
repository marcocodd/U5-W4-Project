package marco.U5W4Project.services;

import marco.U5W4Project.entities.Device;
import marco.U5W4Project.entities.Worker;
import marco.U5W4Project.exceptions.BadRequestException;
import marco.U5W4Project.exceptions.NotFoundException;
import marco.U5W4Project.payloads.NewDeviceDTO;
import marco.U5W4Project.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    WorkerService workerService;

    public Device save(NewDeviceDTO body) {

        if (body.assigned()) {

            if (body.workerId() == 0) {
                throw new BadRequestException("L'ID del lavoratore non è stato fornito.");
            }

            Worker worker = workerService.findById(body.workerId());

            Device newDevice = new Device(
                    body.type(),
                    body.available(),
                    true,
                    body.maintenance(),
                    false,
                    worker
            );


            return deviceDAO.save(newDevice);
        } else {

            Device newDevice = new Device(
                    body.type(),
                    body.available(),
                    false,
                    body.maintenance(),
                    body.retired()
            );


            return deviceDAO.save(newDevice);
        }
    }


    public Device findById(long deviceId) {
        return this.deviceDAO.findById(deviceId).orElseThrow(() -> new NotFoundException(deviceId));
    }

    public Page<Device> getDevicePages(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.deviceDAO.findAll(pageable);
    }

    public Device findByIdAndUpdate(long deviceId, Device updatedDevice) {
        Device found = this.findById(deviceId);
        found.setType(updatedDevice.getType());
        found.setAvailable(updatedDevice.isAvailable());
        found.setAssigned(updatedDevice.isAssigned());
        found.setMaintenance(updatedDevice.isMaintenance());
        found.setRetired(updatedDevice.isRetired());
        return this.deviceDAO.save(found);
    }

    public void findByIdAndDelete(long deviceId) {
        Device found = this.findById(deviceId);
        this.deviceDAO.delete(found);
    }

}