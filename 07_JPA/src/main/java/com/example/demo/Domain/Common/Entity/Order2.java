package com.example.demo.Domain.Common.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Order2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order2 {
    @EmbeddedId
    private OrderId2 id;
    private int quantity;
}
