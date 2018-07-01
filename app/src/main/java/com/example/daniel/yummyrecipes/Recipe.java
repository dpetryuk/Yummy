package com.example.daniel.yummyrecipes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 6/30/2018.
 */

@Entity(tableName = "recipe_table")
public class Recipe implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipe")
    private String mRecipe;

    private List<String> mIngredients;
    private String mCookTime;
    private String mPrepTime;
    private List<String> mSteps;

    public Recipe(@NonNull String mRecipe, @NonNull List<String> mIngredients, @NonNull String mCookTime,
                  String mPrepTime, @NonNull List<String> mSteps){
        this.mRecipe = mRecipe;
        this.mIngredients = mIngredients;
        this.mCookTime = mCookTime;
        this.mPrepTime = mPrepTime;
        this.mSteps = mSteps;
    }

    public String getRecipe() {
        return this.mRecipe;
    }

    public List<String> getIngredients() {
        return this.mIngredients;
    }

    public String getCookTime() {
        return this.mCookTime;
    }

    public String getPrepTime() { return this.mPrepTime; }

    public List<String> getSteps() { return this.mSteps; }

}
