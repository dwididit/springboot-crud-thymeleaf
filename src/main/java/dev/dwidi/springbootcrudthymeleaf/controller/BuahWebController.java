package dev.dwidi.springbootcrudthymeleaf.controller;

import dev.dwidi.springbootcrudthymeleaf.dto.buah.BuahResponseDTO;
import dev.dwidi.springbootcrudthymeleaf.service.BuahService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buah")
@RequiredArgsConstructor
public class BuahWebController {

    private final BuahService buahService;

    @GetMapping
    public String listBuah(Model model) {
        List<BuahResponseDTO> buahList = buahService.getAllBuah();
        model.addAttribute("buahList", buahList);
        return "buah/list";
    }

    @GetMapping("/create")
    public String createBuahForm() {
        return "buah/form";
    }

    @GetMapping("/edit/{id}")
    public String editBuahForm(@PathVariable("id") Long id, Model model) {
        BuahResponseDTO buah = buahService.getBuahById(id);
        model.addAttribute("buah", buah);
        return "buah/form";
    }
}