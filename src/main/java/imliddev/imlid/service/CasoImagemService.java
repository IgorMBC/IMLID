package imliddev.imlid.service;

import imliddev.imlid.domain.model.Caso;
import imliddev.imlid.domain.model.CasoImagem;
import imliddev.imlid.repository.CasoImagemRepository;
import imliddev.imlid.repository.CasoRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CasoImagemService {

    private final CasoRepository casoRepository;
    private final CasoImagemRepository imagemRepository;

    public void uploadImagem(UUID casoId, MultipartFile arquivo) {

        Caso caso = casoRepository.findById(casoId)
                .orElseThrow(() -> new RuntimeException("Caso não encontrado"));

        validarFormato(arquivo);

        String sha256 = calcularSHA256(arquivo);

        try {

            Path uploadDir = Paths.get("uploads");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String extensao = Objects.requireNonNull(arquivo.getOriginalFilename())
                    .substring(arquivo.getOriginalFilename().lastIndexOf("."));

            String fileName = UUID.randomUUID() + extensao;

            Path filePath = uploadDir.resolve(fileName);

            Files.copy(arquivo.getInputStream(), filePath);

            CasoImagem imagem = new CasoImagem();

            imagem.setNomeArquivo(arquivo.getOriginalFilename());
            imagem.setTipoArquivo(arquivo.getContentType());
            imagem.setSha256(sha256);
            imagem.setCaminhoStorage(filePath.toString());
            imagem.setDataUpload(LocalDateTime.now());
            imagem.setCaso(caso);

            imagemRepository.save(imagem);

        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar imagem. Tente novamente.");
        }
    }

    private void validarFormato(MultipartFile arquivo) {

        String formato = arquivo.getOriginalFilename();

        if (formato == null ||
                (!formato.endsWith(".jpg") &&
                        !formato.endsWith(".jpeg") &&
                        !formato.endsWith(".png"))) {

            throw new RuntimeException("Formato não permitido");
        }
    }

    private String calcularSHA256(MultipartFile arquivo) {

        try (InputStream is = arquivo.getInputStream()) {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = digest.digest();

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular SHA-256");
        }
    }

}
