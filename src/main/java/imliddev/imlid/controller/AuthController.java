package imliddev.imlid.controller;

import imliddev.imlid.dto.UsuarioRequest;
import imliddev.imlid.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioRequest request){

        usuarioService.cadastrarUsuario(request);

        return ResponseEntity.status(201).build();
    }

}
