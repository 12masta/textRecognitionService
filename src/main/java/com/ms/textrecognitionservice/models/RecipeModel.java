package com.ms.textrecognitionservice.models;

import java.util.List;

public class RecipeModel {

    private List<RecipeEntity> entities;

    public RecipeModel() {
    }

    public RecipeModel(List<RecipeEntity> entities) {
        this.entities = entities;
    }


    public List<RecipeEntity> getEntities() {
        return entities;
    }
}