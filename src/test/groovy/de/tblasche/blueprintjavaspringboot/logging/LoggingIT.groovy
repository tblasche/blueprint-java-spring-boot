package de.tblasche.blueprintjavaspringboot.logging

import io.github.joke.spockoutputcapture.CapturedOutput
import io.github.joke.spockoutputcapture.OutputCapture
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import spock.lang.Specification

import java.util.stream.Collectors

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoggingIT extends Specification {

  @LocalServerPort
  int port

  @OutputCapture
  CapturedOutput localLogs

  def "should write application logs in JSON format"() {
    when:
    LoggerFactory.getLogger("test-logger").warn("test message")

    then:
    with(getLastLineFromOutputCapture()) {
      startsWith('{')
      contains('"@timestamp":"')
      contains('"type":"application"')
      contains('"msg":"test message"')
      contains('"logger":"test-logger"')
      contains('"level":"WARN"')
      contains('"file":"')
      contains('"line":')
      endsWith('}')
    }
  }

  def "should write access logs in JSON format"() {
    when:
    new TestRestTemplate().getForEntity("http://localhost:" + port + "/alive?foo=bar", String.class)

    then:
    with(getLastLineFromOutputCapture()) {
      startsWith('{')
      contains('"@timestamp":"20')
      contains('"type":"access"')
      contains('"protocol":"HTTP/1.1"')
      contains('"method":"GET"')
      contains('"path":"/alive"')
      contains('"query":"?foo=bar"')
      contains('"status":200')
      contains('"duration":')
      !contains('"bytesSent":')
      contains('"userAgent":"Java')
      matches(/.+"requestId":"[a-z0-9]{32}".+/)
      contains('"remoteIp":')
      !contains('"user":')
      endsWith('}')
    }
  }

  def "should write sanitized request id from X-Request-ID request header to access logs"() {
    when:
    new RestTemplateBuilder()
      .defaultHeader("X-Request-ID", "test-request-*id!-header")
      .build()
      .getForEntity("http://localhost:" + port + "/alive", String.class)

    then:
    getLastLineFromOutputCapture().contains('"requestId":"test-request-id-header"')
  }

  private getLastLineFromOutputCapture() {
    return localLogs.toString().trim().lines().collect(Collectors.toList()).last().trim()
  }
}
