package marco.U5W4Project.repositories;

import marco.U5W4Project.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDAO extends JpaRepository<Device, Long> {
}
