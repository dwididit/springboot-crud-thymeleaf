package dev.dwidi.springbootcrudthymeleaf.dto.buah;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuahResponseDTO {
    private Long buahId;
    private String namaBuah;
    private String deskripsi;
    private BigDecimal harga;
}
