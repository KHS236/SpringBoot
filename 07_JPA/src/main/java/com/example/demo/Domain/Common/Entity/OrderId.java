package com.example.demo.Domain.Common.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderId {
    private Long orderId;
    private Long productId;
}
