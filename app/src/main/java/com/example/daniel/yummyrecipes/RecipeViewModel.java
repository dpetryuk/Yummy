package com.example.daniel.yummyrecipes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Daniel on 7/21/2018.
 */


public class RecipeViewModel extends AndroidViewModel{

    private RecipeRepository mRepository;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new RecipeRepository(application); // repository uses application context. DB should live for the life of the WHOLE APPLICATION.
        mAllRecipes = mRepository.getmAllRecipes();
    }

    LiveData<List<Recipe>> getAllRecipes() { return mAllRecipes; } // another wrapper method to hide from UI

    public void insert(Recipe recipe) { mRepository.insert(recipe); } // wrapper method to hide implementation from UI



}
