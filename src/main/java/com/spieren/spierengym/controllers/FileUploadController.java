package com.spieren.spierengym.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    private static final String UPLOAD_FOLDER = "uploads";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/subir")
    public String handleFileUpload(@RequestPart("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Selecciona un archivo para subir");
            return "redirect:/";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER, file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "Archivo subido con éxito: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}

