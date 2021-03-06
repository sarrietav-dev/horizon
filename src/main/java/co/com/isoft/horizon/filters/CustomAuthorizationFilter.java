package co.com.isoft.horizon.filters;

import co.com.isoft.horizon.utils.ResponseBodyBuilder;
import co.com.isoft.horizon.utils.TokenMissingException;
import co.com.isoft.horizon.utils.TokenService;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  public CustomAuthorizationFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().equals("/api/auth/login")
        || request.getServletPath().equals("/api/auth/token")) {
      filterChain.doFilter(request, response);
    } else {
      try {
        log.info("Extracting the token from the request.");
        String token = tokenService.extractAuthorizationToken(request);
        DecodedJWT decodedJWT = tokenService.decodeJWT(token);

        String email = decodedJWT.getSubject();
        String role = String.valueOf(decodedJWT.getClaim("role")).replaceAll("\"", "");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
      } catch (TokenMissingException e) {
        log.error(e.getMessage());
        filterChain.doFilter(request, response);
      } catch (TokenExpiredException e) {
        log.error(e.getMessage());
        ResponseBodyBuilder.create(response, HttpStatus.UNAUTHORIZED.value())
            .addField("error", e.getMessage())
            .build();
      } catch (Exception e) {
        log.error(e.getMessage());
        ResponseBodyBuilder.create(response, HttpStatus.BAD_REQUEST.value())
            .addField("error", e.getMessage())
            .build();
      }
    }
  }
}
