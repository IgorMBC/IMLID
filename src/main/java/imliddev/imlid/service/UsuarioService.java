package imliddev.imlid.service;

import imliddev.imlid.domain.model.Usuario;
import imliddev.imlid.repository.UsuarioRepository;
import imliddev.imlid.dto.UsuarioRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String login) throws UsernameNotFoundException {

        return repository.findByLoginAndStatusTrue(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    }

    public void cadastrarUsuario(UsuarioRequest request) {
        if (repository.existsByLoginIgnoreCase(request.login())) {
            throw new RuntimeException("Login já cadastrado");
        }

        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = request.toEntity();

        usuario.setSenha(passwordEncoder.encode(request.senha()));

        repository.save(usuario);
    }
}