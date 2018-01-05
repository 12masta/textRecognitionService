package com.ms.textrecognitionservice.utils.imagerecognize;

import com.ms.textrecognitionservice.models.RecipeModel;

public interface ImageRecognitionService {
    RecipeModel recognizeText(String imageInBase64) throws Exception;
}
