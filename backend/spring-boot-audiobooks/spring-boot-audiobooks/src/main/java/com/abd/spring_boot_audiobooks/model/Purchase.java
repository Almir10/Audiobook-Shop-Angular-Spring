package com.abd.spring_boot_audiobooks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "audiobook_Id", nullable = false)
    private int audiobookId;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name="purchase_time")
    private LocalDateTime purchaseTime;


}
