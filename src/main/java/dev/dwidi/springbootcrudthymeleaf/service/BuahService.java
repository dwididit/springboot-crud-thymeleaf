package dev.dwidi.springbootcrudthymeleaf.service;

import dev.dwidi.springbootcrudthymeleaf.dto.BaseResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahCreateRequestDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahUpdateRequestDTO;
import dev.dwidi.springbootcrudthymeleaf.entity.Buah;
import dev.dwidi.springbootcrudthymeleaf.respository.BuahRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final BuahRepository buahRepository;

    // Create buah
    public BaseResponseDTO<BuahResponseDTO> createBuah(BuahCreateRequestDTO buahCreateRequestDTO) {
        // Check if Buah exist
        Buah buahToCreate = buahRepository.findByNamaBuah(buahCreateRequestDTO.getNamaBuah())
                .orElseThrow(() -> new RuntimeException("Buah already exist"));

        buahToCreate.setNamaBuah(buahToCreate.getNamaBuah());
        buahToCreate.setDeskripsi(buahToCreate.getDeskripsi());
        buahToCreate.setHarga(buahCreateRequestDTO.getHarga());

        // Save to database
        try {
            buahRepository.save(buahToCreate);
            log.info("Successfully insert to database");
        } catch (Exception e) {
            throw new RuntimeException("Failed save to database");
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
        Buah buahToEdit = buahRepository.findById(buahId)
                .orElseThrow(() -> new RuntimeException("Buah doesn't exist"));

        if (buahUpdateRequestDTO == null) {
            throw new RuntimeException("Please input data");
        }

        // update buah
        buahToEdit.setNamaBuah(buahUpdateRequestDTO.getNamaBuah());
        buahToEdit.setDeskripsi(buahUpdateRequestDTO.getDeskripsi());
        buahToEdit.setHarga(buahUpdateRequestDTO.getHarga());

        // Save to db
        try {
            buahRepository.save(buahToEdit);
            log.info("Buah edit successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Build response
        BuahResponseDTO buahResponseDTO = new BuahResponseDTO(
                buahToEdit.getBuahId(),
                buahUpdateRequestDTO.getNamaBuah(),
                buahToEdit.getDeskripsi(),
                buahToEdit.getHarga()
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
                .orElseThrow(() -> new RuntimeException("Buah doesn't exist"));

        buatToDelete.setIsDeleted(true);

        buahRepository.save(buatToDelete);

        return new BaseResponseDTO<>(
                HttpStatus.OK.value(),
                "Buah sucessfully deleted",
                null
        );
    }
}
