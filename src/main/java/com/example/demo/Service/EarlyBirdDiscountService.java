package com.example.demo.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EarlyBirdDiscountService {
    public int calculateDiscount(LocalDate eventDate, LocalDate bookingDate) {
        int daysDifference = eventDate.getDayOfYear() - bookingDate.getDayOfYear();

        if (daysDifference >= 30) {
            return 15;
        } else {
            return 0;
        }
    }
}
