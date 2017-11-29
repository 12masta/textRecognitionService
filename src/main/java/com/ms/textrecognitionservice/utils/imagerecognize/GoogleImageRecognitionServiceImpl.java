package com.ms.textrecognitionservice.utils.imagerecognize;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.ms.textrecognitionservice.models.RecipeEntity;
import com.ms.textrecognitionservice.models.RecipeModel;
import io.grpc.internal.IoUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleImageRecognitionServiceImpl implements ImageRecognitionService {
    @Override
    public RecipeModel recognizeText() throws Exception {
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("bill.png");

            // Reads the image file into memory
            byte[] data = IoUtils.toByteArray(is);
            ByteString imgBytes = ByteString.copyFrom(data);

            // Builds the image annotation request
            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            // Performs label detection on the image file
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            List<String> recipeEntities = Arrays.asList(responses.get(0).getFullTextAnnotation().getText().split("\\s*\n\\s*"));
            List<RecipeEntity> recipes = new ArrayList<>();
            for (String entity : recipeEntities) {
                recipes.add(new RecipeEntity(entity));
            }

            return new RecipeModel(recipes);
        }
    }
}
