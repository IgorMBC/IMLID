package imliddev.imlid.controller;

import imliddev.imlid.dto.CasoRequestDTO;
import imliddev.imlid.dto.CasoResponse;
import imliddev.imlid.service.CasoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casos")
@RequiredArgsConstructor
public class CasoController {

    private final CasoService casoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CasoRequestDTO request){

        casoService.cadastrarCaso(request);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public List<CasoResponse> listarCasos() {
        return casoService.listar()
                .stream()
                .map(CasoResponse::fromEntity)
                .toList();
    }
}
