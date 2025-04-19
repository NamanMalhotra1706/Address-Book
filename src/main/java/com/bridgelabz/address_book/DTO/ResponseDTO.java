package com.bridgelabz.address_book.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
public class ResponseDTO {
    private String responseMessage;
    private Object responseObject;

    public ResponseDTO(String responseMessage, Object responseObject) {
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }
}
