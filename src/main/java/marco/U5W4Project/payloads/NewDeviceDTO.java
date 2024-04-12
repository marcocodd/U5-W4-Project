package marco.U5W4Project.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewDeviceDTO(@NotEmpty(message = "type can't be blank")
                           String type,
                           @NotNull(message = "value available must be true or false")
                           boolean available,
                           @NotNull(message = "assigned must be true or false")
                           boolean assigned,
                           @NotNull(message = "maintenance must be true or false")
                           boolean maintenance,
                           @NotNull(message = "retired must be true or false")
                           boolean retired,

                           long workerId) {

}
