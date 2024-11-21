package dev.dwidi.springbootcrudthymeleaf.exception;

import dev.dwidi.springbootcrudthymeleaf.dto.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponseDTO<String> handleGlobalException(Exception ex) {
        BaseResponseDTO<Object> response = new BaseResponseDTO<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                null
        );
        return new BaseResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(BuahNotFoundException.class)
    public BaseResponseDTO<String> handleBuahNotFoundException(BuahNotFoundException ex) {
        BaseResponseDTO<Object> response = new BaseResponseDTO<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new BaseResponseDTO<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }
}