package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.services.ResourceNotFoundException;
import co.com.isoft.horizon.services.UserService;
import co.com.isoft.horizon.utils.TokenMissingException;
import co.com.isoft.horizon.utils.TokenService;
import co.com.isoft.horizon.utils.TokenUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TokenService tokenService = new TokenUtils();

            String refreshToken = tokenService.extractAuthorizationToken(request);
            DecodedJWT decodedJWT = tokenService.decodeJWT(refreshToken);

            String email = decodedJWT.getSubject();
            User user = userService.getUser(email);

            JWTCreator.Builder builder = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", user.getRole().getName());

            String accessToken = tokenService.signJWT(builder);

            response.setHeader("access_token", accessToken);
            response.setHeader("refresh_token", refreshToken);
        } catch (ResourceNotFoundException e) {
            log.error("Logging error: {}", e.getMessage());
            Map<String, String> payload = new HashMap<>();
            payload.put("error", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), payload);
        } catch (TokenMissingException e) {
            log.error("Refresh token missing");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            new ObjectMapper().writeValue(response.getOutputStream(), "Refresh token missing");
        }
    }
}
