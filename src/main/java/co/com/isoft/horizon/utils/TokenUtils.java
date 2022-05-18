package co.com.isoft.horizon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class TokenUtils implements TokenService {

  private final Algorithm algorithm;

  public TokenUtils() {
    this.algorithm = Algorithm.HMAC256("secret".getBytes());
  }

  @Override
  public String extractAuthorizationToken(HttpServletRequest request) throws TokenMissingException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      return authorizationHeader.substring("Bearer ".length());
    }
    throw new TokenMissingException("The token is missing");
  }

  @Override
  public DecodedJWT decodeJWT(String token) {
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  @Override
  public String signJWT(JWTCreator.Builder jwtBuilder) {
    return jwtBuilder.sign(algorithm);
  }
}
