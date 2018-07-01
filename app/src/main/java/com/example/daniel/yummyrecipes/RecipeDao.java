package com.example.daniel.yummyrecipes;

import java.util.List;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
/**
 * Created by Daniel on 6/30/2018.
 */

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //replaces the recipe if the primary key is the same
        void insert(Recipe recipe);


    @Query("DELETE from recipe_table")
        void deleteAll();

    @Query("SELECT * from recipe_table ORDER BY recipe ASC")
        LiveData<List<Recipe>> getAllRecipes();

}

