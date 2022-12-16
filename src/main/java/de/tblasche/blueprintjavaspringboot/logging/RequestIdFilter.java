package de.tblasche.blueprintjavaspringboot.logging;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class RequestIdFilter implements Filter {

  private static final String REQUEST_ID_HEADER = "X-Request-ID";
  private static final String REQUEST_ID_MDC_FIELD_NAME = "requestId";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    try {
      String requestId = getRequestId(httpRequest);
      httpResponse.addHeader(REQUEST_ID_HEADER, requestId);
      MDC.put(REQUEST_ID_MDC_FIELD_NAME, requestId);
      chain.doFilter(request, response);
    } finally {
      MDC.remove(REQUEST_ID_MDC_FIELD_NAME);
    }
  }

  private String getRequestId(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(REQUEST_ID_HEADER))
      .map(requestId -> requestId.replaceAll("[^a-zA-Z0-9-_.]", ""))
      .orElse(UUID.randomUUID().toString().replace("-", ""));
  }
}
