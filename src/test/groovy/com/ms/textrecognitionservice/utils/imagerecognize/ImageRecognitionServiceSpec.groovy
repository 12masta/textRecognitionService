package com.ms.textrecognitionservice.utils.imagerecognize

import com.ms.textrecognitionservice.models.RecipeModel
import spock.lang.Specification
import spock.lang.Subject

class ImageRecognitionServiceSpec extends Specification {

    @Subject
    ImageRecognitionService imageRecognitionService

    def "recognizeText() method has been exected"() {
        given:
        imageRecognitionService = new GoogleImageRecognitionServiceImpl()

        when:
        def results = imageRecognitionService.recognizeText()

        then:
        results as RecipeModel
    }
}