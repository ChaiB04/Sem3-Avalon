package individual.individualsem3backend.configuration.security.token.impl;

import individual.individualsem3backend.configuration.security.token.AccessToken;
import lombok.*;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Integer userId;
    private final String email;
    private final String roles;

    public AccessTokenImpl(String subject, String email, Integer userId, String roles) {
        this.subject = subject;
        this.email = email;
        this.userId = userId;
        this.roles = roles != null ? roles : null;
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
}
