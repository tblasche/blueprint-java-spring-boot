package de.tblasche.blueprintjavaspringboot.health

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerIT extends Specification {

  @LocalServerPort
  int port

  @Autowired
  TestRestTemplate restTemplate

  def "should return 200 OK for alive endpoint"() {
    when:
    def result = restTemplate.getForEntity("http://localhost:" + port + "/alive", String)

    then:
    result.statusCodeValue == 200
    !result.hasBody()
  }

  def "should return 200 OK for ready endpoint"() {
    when:
    def result = restTemplate.getForEntity("http://localhost:" + port + "/ready", String)

    then:
    result.statusCodeValue == 200
    !result.hasBody()
  }
}
