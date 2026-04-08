package imliddev.imlid.repository;

import imliddev.imlid.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByLoginAndStatusTrue(String login);

    boolean existsByLoginIgnoreCase(String login);

    boolean existsByEmail(String email);
}