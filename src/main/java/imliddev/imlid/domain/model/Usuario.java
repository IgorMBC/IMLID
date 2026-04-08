package imliddev.imlid.domain.model;

import imliddev.imlid.domain.enums.EnumCargo;
import imliddev.imlid.dto.UsuarioRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private EnumCargo enumCargo;

    @Column
    public boolean status;

    public Usuario (UsuarioRequest request) {
        this.nome = request.nome();
        this.email = request.email();
        this.login = request.login();
        this.enumCargo = request.enumCargo();
    }

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(enumCargo);
    }

    @Override
    public @NonNull String getUsername() {
        return login;
    }

    @Override
    public @NonNull String getPassword() {
        return senha;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

}
