//package com.bridgelabz.address_book.controllers;
//
//
//import com.bridgelabz.address_book.DTO.ResponseDTO;
//import com.bridgelabz.address_book.services.MailService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/mail")
//@Slf4j
//public class EmailServiceController {
//
//    @Autowired
//    private MailService mailService;
//
//
//    @PostMapping("/sendMail")
//    public ResponseEntity<ResponseDTO> sendEmail(@RequestParam String from, @RequestParam String to, @RequestParam String subject, @RequestParam String body) {
//
//
//        mailService.sendMail(from, to, subject, body);
//        log.info("Mail sent");
//
//        ResponseDTO responseDTO = new ResponseDTO("Mail sent Successfully",null);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
//}
