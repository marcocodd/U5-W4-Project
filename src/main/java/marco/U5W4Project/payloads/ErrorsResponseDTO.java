package marco.U5W4Project.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime messageTime) {
}
