package dev.dwidi.springbootcrudthymeleaf.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "buah")
public class Buah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buahId;

    @Column(unique = true, nullable = false)
    private String namaBuah;

    @Column(nullable = false)
    private String deskripsi;

    @Column(nullable = false)
    private BigDecimal harga;

    @Column(nullable = false)
    private Boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
