package de.tblasche.blueprintjavaspringboot.logging;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessLogConfig {

  @Bean
  @ConditionalOnProperty(name = "server.tomcat.accesslog.enabled")
  public WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> accessLogCustomizer() {
    return factory -> {
      LogbackValve logbackValve = new LogbackValve();
      logbackValve.setFilename(LogbackValve.DEFAULT_FILENAME);
      logbackValve.setQuiet(true);
      logbackValve.setAsyncSupported(true);
      factory.addEngineValves(logbackValve);
    };
  }
}
