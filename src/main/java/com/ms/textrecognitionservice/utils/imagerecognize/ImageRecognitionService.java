package com.ms.textrecognitionservice.utils.imagerecognize;

import com.google.cloud.vision.v1.AnnotateImageResponse;

import java.util.List;

public interface ImageRecognitionService {
    List<AnnotateImageResponse> recognizeText() throws Exception;
}
