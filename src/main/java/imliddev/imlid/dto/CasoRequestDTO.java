package imliddev.imlid.dto;

import imliddev.imlid.domain.enums.*;
import imliddev.imlid.domain.model.Caso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CasoRequestDTO(

        @NotNull
        LocalDateTime dataHoraEntrada,

        @NotNull
        Integer numeroRegistroIml,

        @NotNull
        Integer numeroIdentificacaoCadaver,

        @NotBlank
        String procedencia,

        LocalDateTime dataHoraAproximadaMorte,

        EnumStatusCaso enumStatusCaso,

        EnumEtnia enumEtnia,

        EnumCorOlhos enumCorOlhos,

        EnumCorCabelo enumCorCabelo,

        EnumSexo enumSexo,

        Integer idadeMin,

        Integer idadeMax,

        boolean tatuagem,

        String observacoes

) {
        public Caso toEntity() {
                return new Caso(this);
        }
}
