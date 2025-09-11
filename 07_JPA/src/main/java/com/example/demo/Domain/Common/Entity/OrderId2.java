package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderId2 {
    private Long orderId;
    private Long productId;
}
