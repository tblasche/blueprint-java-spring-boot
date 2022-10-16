package de.tblasche.blueprintjavaspringboot.logging

import spock.lang.Specification

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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
