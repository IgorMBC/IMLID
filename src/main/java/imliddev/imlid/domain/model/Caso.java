package imliddev.imlid.domain.model;

import imliddev.imlid.domain.enums.*;
import imliddev.imlid.dto.CasoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "casos")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(
            mappedBy = "caso",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CasoImagem> imagens;

    @Column(name = "entrada_datetime", nullable = false)
    private LocalDateTime dataHoraEntrada;

    @Column(name = "numero_registro_iml", nullable = false)
    private Integer numeroRegistroIML;

    @Column(name = "numero_identificacao_cadaver", nullable = false, unique = true)
    private Integer numeroIdentificacaoCadaver;

    @Column(nullable = false)
    private String procedencia;

    private LocalDateTime dataHoraAproximadaMorte;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumStatusCaso enumStatusCaso;

    @Enumerated(EnumType.STRING)
    private EnumEtnia enumEtnia;

    @Column(name = "cor_olhos")
    @Enumerated(EnumType.STRING)
    private EnumCorOlhos enumCorOlhos;

    @Column(name = "cor_cabelo")
    @Enumerated(EnumType.STRING)
    private EnumCorCabelo enumCorCabelo;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private EnumSexo enumSexo;

    @Column(name = "idade_min_estimada")
    private Integer idadeMin;

    @Column(name = "idade_max_estimada")
    private Integer idadeMax;

    private boolean tatuagem;

    @Column(name = "descricao")
    private String observacoes;

    private boolean isDeleted = false;

    @Column(name = "created_at")
    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Usuario criadoPor;

    public Caso(CasoRequestDTO request) {
        this.dataHoraEntrada = request.dataHoraEntrada();
        this.numeroRegistroIML = request.numeroRegistroIml();
        this.numeroIdentificacaoCadaver = request.numeroIdentificacaoCadaver();
        this.procedencia = request.procedencia();
        this.dataHoraAproximadaMorte = request.dataHoraAproximadaMorte();
        this.enumStatusCaso = request.enumStatusCaso();
        this.enumEtnia = request.enumEtnia();
        this.enumCorOlhos = request.enumCorOlhos();
        this.enumCorCabelo = request.enumCorCabelo();
        this.enumSexo = request.enumSexo();
        this.idadeMin = request.idadeMin();
        this.idadeMax = request.idadeMax();
        this.tatuagem = request.tatuagem();
        this.observacoes = request.observacoes();
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        this.dataCriacao = agora;
        this.dataAtualizacao = agora;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
