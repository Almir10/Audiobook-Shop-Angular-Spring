package com.abd.spring_boot_audiobooks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="audiobook")
public class Audiobook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="title", nullable = false, length = 50)
    private String title;

    @Column(name="author", nullable = false, length = 30)
    private String author;

    @Column(name="description", nullable = false, length = 1000)
    private String description;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name = "createTime", nullable = false)
    private LocalDateTime createTime;
}
