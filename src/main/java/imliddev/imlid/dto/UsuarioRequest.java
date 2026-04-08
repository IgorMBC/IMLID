package imliddev.imlid.dto;

import imliddev.imlid.domain.enums.EnumCargo;
import imliddev.imlid.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotNull
        EnumCargo enumCargo

) {
        public Usuario toEntity() {
                return new Usuario(this);
        }
}
