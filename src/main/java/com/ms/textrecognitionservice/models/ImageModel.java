package com.ms.textrecognitionservice.models;

public class ImageModel {

    private String imageInBase64;

    public ImageModel() {
    }

    public ImageModel(String imageInBase64) {
        this.imageInBase64 = imageInBase64;
    }

    public String getImageInBase64() {
        return imageInBase64;
    }
}
