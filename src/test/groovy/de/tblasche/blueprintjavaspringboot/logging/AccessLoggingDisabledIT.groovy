package de.tblasche.blueprintjavaspringboot.logging

import io.github.joke.spockoutputcapture.CapturedOutput
import io.github.joke.spockoutputcapture.OutputCapture
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import spock.lang.Specification

import java.util.stream.Collectors

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = "server.tomcat.accesslog.enabled=false"
)
class AccessLoggingDisabledIT extends Specification {

  @LocalServerPort
  int port

  @OutputCapture
  CapturedOutput localLogs

  def "should not write access logs when server.tomcat.accesslog.enabled=false"() {
    when:
    new TestRestTemplate().getForEntity("http://localhost:" + port, String.class)

    then:
    getLastLineFromOutputCapture().contains('"type":"application"')
  }

  private getLastLineFromOutputCapture() {
    return localLogs.toString().trim().lines().collect(Collectors.toList()).last().trim()
  }
}
