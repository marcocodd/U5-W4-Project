package marco.U5W4Project.services;

import marco.U5W4Project.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    
}
