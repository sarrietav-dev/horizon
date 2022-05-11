package co.com.isoft.horizon.utils;

import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface TokenService {
    String extractAuthorizationToken(HttpServletRequest request) throws TokenMissingException;

    DecodedJWT decodeJWT(String token);

    String signJWT(Builder jwtBuilder);
}
