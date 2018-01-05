package com.ms.textrecognitionservice.controllers

import com.ms.textrecognitionservice.Main
import com.ms.textrecognitionservice.models.ImageModel
import com.ms.textrecognitionservice.models.RecipeEntity
import com.ms.textrecognitionservice.models.RecipeModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = Main.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TextRecognitionControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate
    @Shared
    def bill1 = TextRecognitionControllerSpec.class.getClassLoader().getResourceAsStream("bill.txt").text
    @Shared
    def expectedBill1Values = new RecipeModel([
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
    @Shared
    def bill2 = TextRecognitionControllerSpec.class.getClassLoader().getResourceAsStream("bill2.txt").text
    @Shared
    def expectedBill2Values = new RecipeModel([
            new RecipeEntity("BEEF BURGER BAR"),
            new RecipeEntity("UL. WARSZAUERA 1/1A 31-057 KRAKÓW"),
            new RecipeEntity("NIP: 868-189-38-12"),
            new RecipeEntity("25-02-2017"),
            new RecipeEntity("W130366"),
            new RecipeEntity("PARAGON FISKALNY"),
            new RecipeEntity("piekna esterka iszt. 424.00"),
            new RecipeEntity("24.00B"),
            new RecipeEntity("krol krak 1.000+25.00"),
            new RecipeEntity("25.00B"),
            new RecipeEntity($/DODATEK 21.000$2.00/$),
            new RecipeEntity("2.00B"),
            new RecipeEntity("FRITZ 1.00049.00"),
            new RecipeEntity("9.00A"),
            new RecipeEntity("RAZEM:"),
            new RecipeEntity("60.00"),
            new RecipeEntity("SP.OP. A: 9.00 PTU 23.00%"),
            new RecipeEntity("1.68"),
            new RecipeEntity("SP.OP.B: 51.00 PTU 8.00%"),
            new RecipeEntity("SUMA PTU"),
            new RecipeEntity("SUMA: PLN 60.00"),
            new RecipeEntity("F120789 #1"),
            new RecipeEntity("22:24"),
            new RecipeEntity("QAD7E12A132884ADEC26AD7BOESF 2B09 F4"),
            new RecipeEntity("P BEB 12150283"),
            new RecipeEntity("Gotówka:"),
            new RecipeEntity("60.00"),
            new RecipeEntity("3.78"),
            new RecipeEntity("5.46"),
            new RecipeEntity("In"),
    ])


    @Unroll
    def "clients return corrext text"() {
        when:
        def entity = restTemplate.postForEntity('/recognizeTest', new ImageModel(billValue), RecipeModel)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body as RecipeModel
        entity.body.entities.eachWithIndex { RecipeEntity row, int i ->
            assert row.entity == expectedRecipe.entities.get(i).getEntity()
        }

        where:
        billValue | expectedRecipe
        bill1     | expectedBill1Values
        bill2     | expectedBill2Values

    }
}