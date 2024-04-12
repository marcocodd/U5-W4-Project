package marco.U5W4Project.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDeviceDTO(@NotEmpty(message = "type can't be blank")
                           String type,
                           @NotEmpty(message = "value available must be true or false")
                           boolean available,
                           @NotEmpty(message = "assigned must be true or false")
                           boolean assigned,
                           @NotEmpty(message = "maintenance must be true or false")
                           boolean maintenance,
                           @NotEmpty(message = "retired must be true or false")
                           boolean retired,
                           @Size(min = 1)
                           long workerId) {

}
