package com.ms.textrecognitionservice.utils.imagerecognize;

import com.ms.textrecognitionservice.models.RecipeModel;

public interface ImageRecognitionService {
    RecipeModel recognizeText() throws Exception;
}
