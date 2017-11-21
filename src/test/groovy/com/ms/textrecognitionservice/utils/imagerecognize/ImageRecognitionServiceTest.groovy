package com.ms.textrecognitionservice.utils.imagerecognize

import com.google.cloud.vision.v1.AnnotateImageResponse
import spock.lang.Specification
import spock.lang.Subject


class ImageRecognitionServiceTest extends Specification {

    @Subject
    ImageRecognitionService imageRecognitionService

    def "recognizeText() method has been exected"() {
        given:
        ImageRecognitionService imageRecognitionService = new GoogleImageRecognitionServiceImpl()

        when:
        def results = imageRecognitionService.recognizeText()

        then:
        results as List
    }
}