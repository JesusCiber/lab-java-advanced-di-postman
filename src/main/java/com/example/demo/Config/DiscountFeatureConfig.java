package com.example.demo.Config;


import com.example.demo.Service.EarlyBirdDiscountService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Configuration
public class DiscountFeatureConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "feature.earlybird.enabled", havingValue = "true")
    public EarlyBirdDiscountService earlyBirdDiscountService() {
        return new EarlyBirdDiscountService();
    }

    @Bean
    @ConditionalOnProperty(name = "feature.earlybird.enabled", havingValue = "false", matchIfMissing = true)
    public EarlyBirdDiscountService EarlyBirdDiscountServiceDisable() {
        return new EarlyBirdDiscountService() {
            @Override
            public int calculateDiscount(LocalDate eventDate, LocalDate bookingDate) {
                throw new UnsupportedOperationException(
                        "El servicio está desactivado. Para activarlo, cambia 'feature.earlybird.enabled=true' en application.properties."
                );
            }
        };

    }
}