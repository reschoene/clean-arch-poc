package cleanarch.poc.external.services.spring


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired (required = false)
    private cleanarch.poc.external.services.spring.controller.FundsTransferServiceController fundsTransferServiceController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
        fundsTransferServiceController
    }
}
