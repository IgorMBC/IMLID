package imliddev.imlid.controller;

import imliddev.imlid.service.CasoImagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/casos")
@RequiredArgsConstructor
public class ImagensController {

    private final CasoImagemService imagemService;

    @PostMapping("/{id}/imagens")
    public ResponseEntity<Void> uploadImagem(
            @PathVariable UUID id,
            @RequestParam MultipartFile file
    ) {

        imagemService.uploadImagem(id, file);

        return ResponseEntity.status(201).build();
    }
}
