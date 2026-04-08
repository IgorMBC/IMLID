package imliddev.imlid.dto;

import imliddev.imlid.domain.model.Caso;

import java.time.LocalDateTime;
import java.util.UUID;

public record CasoResponse(

        UUID id,
        LocalDateTime dataHoraEntrada,
        Integer numeroRegistroIml,
        Integer numeroIdentificacaoCadaver,
        String procedencia,
        LocalDateTime dataHoraAproximadaMorte,
        String statusCaso,
        String etnia,
        String sexo,
        Integer idadeMin,
        Integer idadeMax,
        boolean tatuagem

) {

    public static CasoResponse fromEntity(Caso caso) {
        return new CasoResponse(
                caso.getId(),
                caso.getDataHoraEntrada(),
                caso.getNumeroRegistroIML(),
                caso.getNumeroIdentificacaoCadaver(),
                caso.getProcedencia(),
                caso.getDataHoraAproximadaMorte(),
                caso.getEnumStatusCaso() != null ? caso.getEnumStatusCaso().name() : null,
                caso.getEnumEtnia() != null ? caso.getEnumEtnia().name() : null,
                caso.getEnumSexo() != null ? caso.getEnumSexo().name() : null,
                caso.getIdadeMin(),
                caso.getIdadeMax(),
                caso.isTatuagem()
        );
    }
}