package de.tblasche.blueprintjavaspringboot.logging;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
