package com.riwi.ticketShowWeb.api.error_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riwi.ticketShowWeb.api.dto.errors.BaseErrorResponse;
import com.riwi.ticketShowWeb.api.dto.errors.ErrorsResp;
import com.riwi.ticketShowWeb.utils.exceptions.BadRequestException;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController 
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleBadRequest(MethodArgumentNotValidException e)
    {
        List<Map<String, String>> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(field -> {
            Map<String, String> error = new HashMap<>();

            error.put("error", field.getDefaultMessage());
            error.put("field", field.getField());
            errors.add(error);
        });

        return ErrorsResp.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .errors(errors)
                        .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public BaseErrorResponse handlerError(BadRequestException e) {

        List<Map<String, String>> errors = new ArrayList<>();

        Map<String, String> error = new HashMap<>();

        error.put("id", e.getMessage());

        errors.add(error);

        return ErrorsResp.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .errors(errors)
                        .build();
    }
}
