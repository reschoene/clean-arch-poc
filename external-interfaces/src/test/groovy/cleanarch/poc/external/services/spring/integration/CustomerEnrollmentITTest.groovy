package cleanarch.poc.external.services.spring.integration

import com.fasterxml.jackson.databind.ObjectMapper
import cleanarch.poc.external.services.spring.controller.dto.CustomerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.transaction.Transactional

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@AutoConfigureMockMvc
@SpringBootTest
class CustomerEnrollmentITTest extends Specification {
    def objectMapper = new ObjectMapper()

    @Autowired
    private MockMvc mockMvc

    @Transactional
    def "POST /customer-enrollment/enroll enroll a new customer and return its contract"(){
        given:
            def customerDto = CustomerDto.builder()
                    .firstName("RenatoTest")
                    .build()
        when: "Is performed a POST request to /customer-enrollment/enroll endpoint"
            def resActions = mockMvc.perform(post("/customer-enrollment/enroll")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customerDto)))
            def response = resActions.andReturn().response
        then: "returns a OK response (200) and its contract"
            response.status == OK.value()
            response.contentAsString
    }
}

