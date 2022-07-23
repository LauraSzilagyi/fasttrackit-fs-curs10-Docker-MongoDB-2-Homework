package ro.fasttrackit.curs10.homework.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TrainNotInTheStartLocationException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleResourceNotFound(TrainNotInTheStartLocationException ex) {
        return new ApiError("ERROR", ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(CONFLICT)
    ApiError handleResourceNotFound(AlreadyExistException ex) {
        return new ApiError("ERROR", ex.getMessage());
    }

    @ExceptionHandler(InvalidModelException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleResourceNotFound(InvalidModelException ex) {
        return new ApiError("ERROR", ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ApiError handleResourceNotFound(EntityNotFoundException ex) {
        return new ApiError("ERROR", ex.getMessage());
    }
}

record ApiError(String code, String message) {
}