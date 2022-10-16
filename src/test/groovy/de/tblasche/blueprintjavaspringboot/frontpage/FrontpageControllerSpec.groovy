package de.tblasche.blueprintjavaspringboot.frontpage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

@WebMvcTest
class FrontpageControllerSpec extends Specification {

  @Autowired
  MockMvc mockMvc

  def "should provide frontpage"() {
    when:
    def result = mockMvc.perform(MockMvcRequestBuilders.get("/api/pages/frontpage")).andReturn()

    then:
    result.response.status == 200
    result.response.contentAsString.contains("<h1>This is the frontpage</h1>")
  }
}
