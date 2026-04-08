package imliddev.imlid.domain.enums;

import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;

public enum EnumCargo implements GrantedAuthority {

    ADMIN,
    PERITO,
    AUDITOR;

    @Override
    public @NonNull String getAuthority() {
        return "ROLE_" + this.name();
    }
}