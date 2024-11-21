package dev.dwidi.springbootcrudthymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuahCreateRequestDTO {
    private String namaBuah;
    private String deskripsi;
    private BigDecimal harga;
}
