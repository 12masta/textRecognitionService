package com.ms.textrecognitionservice.controllers

import com.ms.textrecognitionservice.Main
import com.ms.textrecognitionservice.models.RecipeEntity
import com.ms.textrecognitionservice.models.RecipeModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = Main.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TextRecognitionControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def "clients return corrext text"() {
        when:
        def entity = restTemplate.postForEntity('/recognizeTest', "test", RecipeModel)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body as RecipeModel
        entity.body.entities.eachWithIndex { RecipeEntity row, int i ->
            assert row.entity == recipe.entities.get(i).getEntity()
        }

        where:
        recipe = new RecipeModel([
                new RecipeEntity("PARAGON FISKALMY"),
                new RecipeEntity("MLEKO UHT 3,2%"),
                new RecipeEntity("PEPSI/P. MAX , MIR. , 7UP"),
                new RecipeEntity("CHLEB ŻYTNI RAZOWY"),
                new RecipeEntity("96399 Nóż KUCHENNY"),
                new RecipeEntity("1*2,39= 2,39 C"),
                new RecipeEntity("3*1,29= 3,87 A"),
                new RecipeEntity("1*2,49= 2,49 B"),
                new RecipeEntity("1*9,99: 9,99 A"),
                new RecipeEntity("X"),
                new RecipeEntity("Sprzed. oppd. PTU A"),
                new RecipeEntity("Kwota A 23,00%"),
                new RecipeEntity("Sprzed. opod. PTU B"),
                new RecipeEntity("Kwota B 08,00%"),
                new RecipeEntity("Sprzed. opod. PTU C"),
                new RecipeEntity("Kwota C 05,00%"),
                new RecipeEntity("Podatek PTU"),
                new RecipeEntity("SUMA PLN"),
                new RecipeEntity("000905 #001 12 1021"),
                new RecipeEntity("13,86"),
                new RecipeEntity("2,59"),
                new RecipeEntity("2,49"),
                new RecipeEntity("0,18"),
                new RecipeEntity("2,39"),
                new RecipeEntity("0,11"),
                new RecipeEntity("2,88"),
                new RecipeEntity("18,74"),
                new RecipeEntity("nr:582086"),
                new RecipeEntity("21:02")
        ])
    }
}