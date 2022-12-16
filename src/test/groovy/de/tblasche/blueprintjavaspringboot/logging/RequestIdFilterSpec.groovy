package de.tblasche.blueprintjavaspringboot.logging

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import spock.lang.Specification

class RequestIdFilterSpec extends Specification {

  def "should sanitize provided request id"() {
    given:
    def request = Mock(HttpServletRequest)
    def response = Mock(HttpServletResponse)
    def filterChain = Mock(FilterChain)
    request.getHeader("X-Request-ID") >> "!+test-request,.,id-§§foo_bar//"

    when:
    new RequestIdFilter().doFilter(request, response, filterChain)

    then:
    1* response.addHeader("X-Request-ID", "test-request.id-foo_bar")
    1* filterChain.doFilter(request, response)
  }

  def "should generate request id if none is provided"() {
    given:
    def request = Mock(HttpServletRequest)
    def response = Mock(HttpServletResponse)
    def filterChain = Mock(FilterChain)
    request.getHeader("X-Request-ID") >> null

    when:
    new RequestIdFilter().doFilter(request, response, filterChain)

    then:
    1* response.addHeader("X-Request-ID", { it.matches(/^[a-z0-9]{32}$/) })
    1* filterChain.doFilter(request, response)
  }
}
