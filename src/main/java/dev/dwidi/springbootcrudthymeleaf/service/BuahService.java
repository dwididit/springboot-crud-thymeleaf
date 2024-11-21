package dev.dwidi.springbootcrudthymeleaf.service;

import dev.dwidi.springbootcrudthymeleaf.dto.BaseResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahCreateRequestDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahUpdateRequestDTO;
import dev.dwidi.springbootcrudthymeleaf.entity.Buah;
import dev.dwidi.springbootcrudthymeleaf.exception.BuahNotFoundException;
import dev.dwidi.springbootcrudthymeleaf.respository.BuahRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuahService {

    private final BuahRepository buahRepository;

    // Create buah
    public BaseResponseDTO<BuahResponseDTO> createBuah(BuahCreateRequestDTO buahCreateRequestDTO) {
        // Check if Buah exist
        buahRepository.findByNamaBuah(buahCreateRequestDTO.getNamaBuah())
                .ifPresent(buah -> {
                    throw new BuahNotFoundException("Buah already exist");
                });

        Buah buahToCreate = new Buah();
        buahToCreate.setNamaBuah(buahCreateRequestDTO.getNamaBuah());
        buahToCreate.setDeskripsi(buahCreateRequestDTO.getDeskripsi());
        buahToCreate.setHarga(buahCreateRequestDTO.getHarga());
        buahToCreate.setIsDeleted(false);

        // Save to database
        try {
            buahRepository.save(buahToCreate);
            log.info("Successfully insert to database");
        } catch (Exception e) {
            log.error("Error saving to database: ", e);
            throw new RuntimeException("Failed save to database: " + e.getMessage());
        }

        // Build response
        BuahResponseDTO buahResponseDTO = new BuahResponseDTO(
                buahToCreate.getBuahId(),
                buahToCreate.getNamaBuah(),
                buahToCreate.getDeskripsi(),
                buahToCreate.getHarga()
        );

        return new BaseResponseDTO<>(
                HttpStatus.CREATED.value(),
                "Buah created successfully",
                buahResponseDTO);
    }

    // Update buah
    public BaseResponseDTO<BuahResponseDTO> updateBuah(Long buahId, BuahUpdateRequestDTO buahUpdateRequestDTO) {
        // Check if buah exist
        Buah existingBuah = buahRepository.findByBuahIdAndIsDeletedFalse(buahId)
                .orElseThrow(() -> new BuahNotFoundException("Buah doesn't exist"));

        // update buah
        Optional.ofNullable(buahUpdateRequestDTO.getNamaBuah()).ifPresent(existingBuah::setNamaBuah);
        Optional.ofNullable(buahUpdateRequestDTO.getDeskripsi()).ifPresent(existingBuah::setDeskripsi);
        Optional.ofNullable(buahUpdateRequestDTO.getHarga()).ifPresent(existingBuah::setHarga);

        // Save to db
        try {
            buahRepository.save(existingBuah);
            log.info("Buah edit successfully");
        } catch (Exception e) {
            log.error("Error editing database: ", e);
            throw new RuntimeException("Failed edit database: " + e.getMessage());
        }

        // Build response
        BuahResponseDTO buahResponseDTO = new BuahResponseDTO(
                existingBuah.getBuahId(),
                existingBuah.getNamaBuah(),
                existingBuah.getDeskripsi(),
                existingBuah.getHarga()
        );

        return new BaseResponseDTO<>(
                HttpStatus.OK.value(),
                "Buah sucessfully edit",
                buahResponseDTO
        );
    }

    // Soft delete buah
    public BaseResponseDTO<BuahResponseDTO> deleteBuah(Long buahId) {
        // Check if buat exist
        Buah buatToDelete = buahRepository.findById(buahId)
                .orElseThrow(() -> new BuahNotFoundException("Buah doesn't exist"));

        buatToDelete.setIsDeleted(true);

        buahRepository.save(buatToDelete);

        return new BaseResponseDTO<>(
                HttpStatus.OK.value(),
                "Buah sucessfully deleted",
                null
        );
    }

    public List<BuahResponseDTO> getAllBuah() {
        return buahRepository.findAllByIsDeletedFalse().stream()
                .map(buah -> new BuahResponseDTO(
                        buah.getBuahId(),
                        buah.getNamaBuah(),
                        buah.getDeskripsi(),
                        buah.getHarga()
                ))
                .collect(Collectors.toList());
    }

    public BuahResponseDTO getBuahById(Long id) {
        Buah buah = buahRepository.findByBuahIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BuahNotFoundException("Buah not found"));

        return new BuahResponseDTO(
                buah.getBuahId(),
                buah.getNamaBuah(),
                buah.getDeskripsi(),
                buah.getHarga()
        );
    }
}
