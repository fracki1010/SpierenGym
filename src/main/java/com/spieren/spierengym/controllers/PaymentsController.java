package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.PaymentDTO;
import com.spieren.spierengym.models.Payment;
import com.spieren.spierengym.models.PaymentMethod;
import com.spieren.spierengym.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PaymentsController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/payments")
    public Set<PaymentDTO> getAllPayments(){
        return paymentRepository.findAll()
                                .stream()
                                .map(payment -> new PaymentDTO(payment))
                                .collect(Collectors.toSet());
    }

    @PostMapping("payments/approved")
    public ResponseEntity<Object> approvedPayments(@RequestParam Long paymentId, @RequestParam PaymentMethod paymentMethod){
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(true);
        paymentRepository.save(payment);
        return new ResponseEntity<>("El pago se aprobo con exito", HttpStatus.OK);
    }
}
