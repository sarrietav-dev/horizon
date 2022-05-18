package co.com.isoft.horizon.filters;

import co.com.isoft.horizon.utils.ResponseBodyBuilder;
import co.com.isoft.horizon.utils.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@AllArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    log.info("Email is: {}", email);
    log.info("Password is: {}", password);

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(email, password);
    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();

    String authorityList =
        user.getAuthorities().stream()
            .findFirst()
            .orElseThrow(() -> new ServletException("Authority not found"))
            .getAuthority();

    log.info("Creating access and refresh tokens for user: {}", user.getUsername());

    JWTCreator.Builder accessBuilder =
        JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("role", authorityList);

    JWTCreator.Builder refreshBuilder =
        JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
            .withIssuer(request.getRequestURL().toString());

    response.setHeader("access_token", tokenService.signJWT(accessBuilder));
    response.setHeader("refresh_token", tokenService.signJWT(refreshBuilder));

    ResponseBodyBuilder.create(response, 200).addField("role", authorityList);
  }
}
