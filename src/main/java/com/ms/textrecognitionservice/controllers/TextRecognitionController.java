package com.ms.textrecognitionservice.controllers;

import com.ms.textrecognitionservice.models.RecipeModel;
import com.ms.textrecognitionservice.utils.imagerecognize.GoogleImageRecognitionServiceImpl;
import com.ms.textrecognitionservice.utils.imagerecognize.ImageRecognitionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TextRecognitionController {

    @RequestMapping("/recognizeTest")
    RecipeModel recognizeText() {

        ImageRecognitionService imageRecognitionService = new GoogleImageRecognitionServiceImpl();
        try {
            return imageRecognitionService.recognizeText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RecipeModel(new ArrayList<>());
    }
}
