package com.example.demo.Controller;

import com.example.demo.Service.EarlyBirdDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final EarlyBirdDiscountService service;

    @Autowired
    public DiscountController(@Qualifier("earlyBirdDiscountService")EarlyBirdDiscountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> getDiscount(@RequestParam String eventDate, @RequestParam String bookingDate) {
        if (service == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("El servicio de descuento anticipado está desactivado.");
        }
        try {
            LocalDate event = LocalDate.parse(eventDate);
            LocalDate booking = LocalDate.parse(bookingDate);

            int discount = service.calculateDiscount(event, booking);

            return ResponseEntity.ok("Discount applied: " + discount + "%");
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }
}