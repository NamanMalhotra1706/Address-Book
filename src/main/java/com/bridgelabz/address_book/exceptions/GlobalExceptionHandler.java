package com.bridgelabz.address_book.exceptions;

import com.bridgelabz.address_book.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO>  handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Not a valid arrgument: "+ex.getMessage());

        ResponseDTO responseDTO = new ResponseDTO("Not a valid arrgument", ex.getBindingResult().getAllErrors());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
