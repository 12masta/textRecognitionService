package com.ms.textrecognitionservice.controllers;

import com.ms.textrecognitionservice.models.ImageModel;
import com.ms.textrecognitionservice.models.RecipeModel;
import com.ms.textrecognitionservice.utils.imagerecognize.GoogleImageRecognitionServiceImpl;
import com.ms.textrecognitionservice.utils.imagerecognize.ImageRecognitionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/recognizeTest")
public class TextRecognitionController {

    @RequestMapping(method = RequestMethod.POST)
    RecipeModel recognizeText(@RequestBody ImageModel imageModel) {

        ImageRecognitionService imageRecognitionService = new GoogleImageRecognitionServiceImpl();
        try {
            return imageRecognitionService.recognizeText(imageModel.getImageInBase64());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RecipeModel(new ArrayList<>());
    }
}
