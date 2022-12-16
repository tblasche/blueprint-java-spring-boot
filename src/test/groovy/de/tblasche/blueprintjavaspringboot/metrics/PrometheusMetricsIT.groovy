package de.tblasche.blueprintjavaspringboot.metrics

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMetrics
class PrometheusMetricsIT extends Specification {

  @LocalServerPort
  int port

  @Autowired
  TestRestTemplate restTemplate

  def "should provide Prometheus metrics"() {
    when:
    def result = restTemplate.getForEntity("http://localhost:" + port + "/prometheus-metrics", String)

    then:
    result.statusCode.value() == 200
    result.headers.getContentType().isCompatibleWith(MediaType.valueOf("text/plain; charset=utf8"))
    result.body.contains("jvm_memory_max_bytes")
  }
}
