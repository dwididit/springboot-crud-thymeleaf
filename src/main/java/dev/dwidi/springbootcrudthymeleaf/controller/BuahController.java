package dev.dwidi.springbootcrudthymeleaf.controller;

import dev.dwidi.springbootcrudthymeleaf.dto.BaseResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahCreateRequestDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahUpdateRequestDTO;
import dev.dwidi.springbootcrudthymeleaf.service.BuahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buah")
@Slf4j
@RequiredArgsConstructor
public class BuahController {

    private final BuahService buahService;

    @PostMapping("/create")
    public BaseResponseDTO<BuahResponseDTO> createBuah(@RequestBody BuahCreateRequestDTO buahCreateRequestDTO) {
        log.info("Handling request to create buah: {}", buahCreateRequestDTO);
        return buahService.createBuah(buahCreateRequestDTO);
    }

    @PutMapping("/edit/{buahId}")
    public BaseResponseDTO<BuahResponseDTO> editBuah(@PathVariable Long buahId, @RequestBody BuahUpdateRequestDTO buahUpdateRequestDTO) {
        log.info("Handling request to edit buah with userId {} data {}", buahId, buahUpdateRequestDTO);
        return buahService.updateBuah(buahId, buahUpdateRequestDTO);
    }

    @DeleteMapping("/delete/{buahId}")
    public BaseResponseDTO<BuahResponseDTO> deleteBuah(@PathVariable Long buahId) {
        log.info("Handling request to delete buah with Id: {}", buahId);
        return buahService.deleteBuah(buahId);
    }
}
