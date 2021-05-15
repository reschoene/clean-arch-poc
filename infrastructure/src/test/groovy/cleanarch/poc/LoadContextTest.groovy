package cleanarch.poc

import cleanarch.poc.infrastructure.spring.SpringBootAppLauncher
import cleanarch.poc.infrastructure.spring.controller.FundsTransferController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = [SpringBootAppLauncher.class])
class LoadContextTest extends Specification {

    @Autowired (required = false)
    private FundsTransferController fundsTransferServiceController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
            fundsTransferServiceController
    }
}
