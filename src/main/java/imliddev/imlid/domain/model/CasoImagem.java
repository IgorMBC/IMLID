package imliddev.imlid.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "caso_imagens")
public class CasoImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private String tipoArquivo;

    @Column(nullable = false)
    private String sha256;

    @Column(nullable = false)
    private String caminhoStorage;

    @Column(nullable = false)
    private LocalDateTime dataUpload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "caso_id", nullable = false)
    private Caso caso;
}
