package com.sofiasaless.desafiobackend.handle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sofiasaless.desafiobackend.exception.ErrorMsgDTO;

@ControllerAdvice
public class ExceptionHandlerController {
    
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMsgDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMsgDTO> dto = new ArrayList<>();
        
        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            ErrorMsgDTO error = new ErrorMsgDTO(message, err.getField());
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}