package pl.kkubiak.socialapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestNotFoundHandler {
    @ExceptionHandler(value= NotFoundException.class)
    @ResponseStatus(code= HttpStatus.NOT_FOUND)
    public ErrorMessage notFound(NotFoundException ex) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage());
    }
}
