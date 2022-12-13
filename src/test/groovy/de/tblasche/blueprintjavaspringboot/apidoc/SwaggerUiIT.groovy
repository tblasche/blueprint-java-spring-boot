package de.tblasche.blueprintjavaspringboot.apidoc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerUiIT extends Specification {

  @LocalServerPort
  int port

  @Autowired
  TestRestTemplate restTemplate

  def "should provide Swagger UI"() {
    when:
    def result = restTemplate.getForEntity("http://localhost:" + port + requestPath, String)

    then:
    result.statusCodeValue == 200
    result.body.contains("<title>Swagger UI</title>")

    where:
    requestPath << ["/", "/swagger-ui/index.html"]
  }

  def "should provide OpenAPI v3 API doc"() {
    when:
    def result = restTemplate.getForEntity("http://localhost:" + port + "/v3/api-docs", String)

    then:
    result.statusCodeValue == 200
    result.headers.getContentType().isCompatibleWith(MediaType.APPLICATION_JSON)
    result.body.contains('"openapi":"3.0.1"')
  }
}
