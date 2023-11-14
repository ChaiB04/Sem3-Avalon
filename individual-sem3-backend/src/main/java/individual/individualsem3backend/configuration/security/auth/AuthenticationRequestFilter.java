package individual.individualsem3backend.configuration.security.auth;

import individual.individualsem3backend.configuration.security.token.*;
import individual.individualsem3backend.configuration.security.token.exception.InvalidAccessTokenException;
import individual.individualsem3backend.domain.enumeration.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;


@Component
public class AuthenticationRequestFilter extends OncePerRequestFilter {
    private static final String SPRING_SECURITY_ROLE_PREFIX = "ROLE_";

    @Autowired
    private AccessTokenEncoderDecoder accessTokenDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String accessTokenString = requestTokenHeader.substring(7);

        try {
            AccessToken accessToken = accessTokenDecoder.decode(accessTokenString);
            setupSpringSecurityContext(accessToken);
            chain.doFilter(request, response);
        } catch (InvalidAccessTokenException e) {
            logger.error("Error validating access token", e);
            sendAuthenticationError(response);
        }
    }

    private void sendAuthenticationError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.flushBuffer();
    }

    private void setupSpringSecurityContext(AccessToken accessToken) {

        Role role = accessToken.getRoles();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(SPRING_SECURITY_ROLE_PREFIX + role));


        UserDetails userDetails = new User(accessToken.getSubject(), "", authorities);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(accessToken);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}