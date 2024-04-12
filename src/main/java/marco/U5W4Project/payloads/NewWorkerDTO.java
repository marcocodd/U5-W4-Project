package marco.U5W4Project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewWorkerDTO(
        @NotEmpty(message = "username can't be blank")
        @Size(min = 3, max = 10, message = "username must be between 3 and 10 characters")
        String username,
        @NotEmpty(message = "name can't be blank")
        @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters")
        String name,
        @NotEmpty(message = "surname can't be blank")
        @Size(min = 3, max = 25, message = "surname must be between 3 and 25 characters")
        String surname,
        @NotEmpty(message = "email can't be blank")
        @Email(message = "email not valid")
        String email) {
}
