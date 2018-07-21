package com.example.daniel.yummyrecipes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Daniel on 7/21/2018.
 */

public class RecipeRepository {

    private RecipeDao mRecipeDao;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mAllRecipes = mRecipeDao.getAllRecipes();
    }

    LiveData<List<Recipe>> getmAllRecipes(){ // wrapper function to ensure ViewModel uses repository data instead of Mainactivity for good data organization
        return mAllRecipes;
    }

    /*
        TODO: Replace Asynctask with Observer, subscriber, observable pattern from RxJava
     */
    public void insert(Recipe recipe){
        new InsertAsyncTask(mRecipeDao).execute(recipe);
    }

    private class InsertAsyncTask extends AsyncTask<Recipe, Void, Void>{

        private RecipeDao recipeDao;

        private InsertAsyncTask(RecipeDao recipeDao){
            mRecipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mRecipeDao.insert(recipes[0]);
            return null;
        }
    }

}
