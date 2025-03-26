package de.tblasche.blueprintjavaspringboot.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureObservability
class SpringBootActuatorConfigIT extends Specification {

  @LocalServerPort
  int port

  @Autowired
  TestRestTemplate restTemplate

  def "should not expose all default Spring Boot Actuator endpoints"() {
    when:
    def result = restTemplate.getForEntity("http://localhost:" + port + "/health", String)

    then:
    result.statusCode.value() == 404
  }
}
