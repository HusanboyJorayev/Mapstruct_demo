package org.example.example_mapstruct.exception;

import org.example.example_mapstruct.dto.ErrorDto;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDto> errors(MethodArgumentNotValidException e) {
        List<ErrorDto> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            String message = error.getDefaultMessage();
            String field = error.getObjectName();
            errors.add(new ErrorDto(field, message));
        }
        return errors;
    }
}
