package com.ms.textrecognitionservice.controllers

import com.ms.textrecognitionservice.Main
import com.ms.textrecognitionservice.models.RecipeModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = Main.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TextRecognitionControllerTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def "clients return corrext text"() {
        when:
        def entity = restTemplate.getForEntity('/recognizeTest', RecipeModel)

        then:
        noExceptionThrown()
        and:
        entity.statusCode == HttpStatus.OK
    }
}