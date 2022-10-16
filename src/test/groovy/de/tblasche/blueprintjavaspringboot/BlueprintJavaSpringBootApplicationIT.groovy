package de.tblasche.blueprintjavaspringboot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlueprintJavaSpringBootApplicationIT extends Specification {

  @Autowired
  ApplicationContext applicationContext;

  def "should load context"() {
    expect:
    applicationContext != null
  }
}
