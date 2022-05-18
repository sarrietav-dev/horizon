package co.com.isoft.horizon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseBodyBuilder {
  private final Map<String, String> body;
  private final HttpServletResponse response;
  private final int status;

  public ResponseBodyBuilder(HttpServletResponse response, int status) {
    this.response = response;
    this.status = status;
    this.body = new HashMap<>();
  }

  public static ResponseBodyBuilder create(HttpServletResponse response, int status) {
    return new ResponseBodyBuilder(response, status);
  }

  public ResponseBodyBuilder addField(String key, Object value) {
    this.body.put(key, value.toString());
    return this;
  }

  public void build() throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(status);
    new ObjectMapper().writeValue(response.getOutputStream(), this.body);
  }
}
