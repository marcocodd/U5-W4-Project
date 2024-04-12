package marco.U5W4Project.exceptions;

import marco.U5W4Project.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handleBadRequest(BadRequestException exception) {
        if (exception.getErrorsList() != null) {
            String message = exception.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorsResponseDTO(message, LocalDateTime.now());
        } else {
            return new ErrorsResponseDTO(exception.getMessage(), LocalDateTime.now());
        }
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleNotFound(NotFoundException exception) {
        return new ErrorsResponseDTO(exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsResponseDTO handleGenericErrors(Exception exception) {
        exception.printStackTrace();
        return new ErrorsResponseDTO("Server Errors", LocalDateTime.now());
    }
}
