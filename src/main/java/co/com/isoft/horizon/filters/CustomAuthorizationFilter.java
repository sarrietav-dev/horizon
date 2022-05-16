package co.com.isoft.horizon.filters;

import co.com.isoft.horizon.utils.TokenMissingException;
import co.com.isoft.horizon.utils.TokenService;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public CustomAuthorizationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/token")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                String token = tokenService.extractAuthorizationToken(request);
                DecodedJWT decodedJWT = tokenService.decodeJWT(token);

                String email = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (TokenMissingException e) {
                filterChain.doFilter(request, response);
            } catch (TokenExpiredException e) {
                Map<String, String> payload = new HashMap<>();
                payload.put("error", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(401);
                new ObjectMapper().writeValue(response.getOutputStream(), payload);
            } catch (Exception e) {
                log.error("Logging error: {}", e.getMessage());
                Map<String, String> payload = new HashMap<>();
                payload.put("error", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), payload);
            }
        }
    }
}
