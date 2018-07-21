package com.example.daniel.yummyrecipes;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by Daniel on 7/1/2018.
 */

@Database(entities = {Recipe.class}, version = 1) // version # meant for data migration
public abstract class RecipeRoomDatabase extends RoomDatabase {


    private static RecipeRoomDatabase sInstance; // instance of the DB

    public static RecipeRoomDatabase getDatabase(final Context context){
        if (sInstance == null){
            synchronized (RecipeRoomDatabase.class){ // only 1 thread allowed to execute this code
                // because first thread will instantiate the DB
                if (sInstance == null){             // checks to make sure that first
                                                    // thread to access this code instantiates
                                                    // db before 2nd thread reaches the first null check
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class, "recipe_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build(); // create database here...
                }
            }
        }

        return sInstance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(sInstance).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> { // Pre-populate the database asynchronously in a worker thread

        private final RecipeDao mDao;

        PopulateDbAsync(RecipeRoomDatabase db) {
            mDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            //Word word = new Word("Hello");
            //mDao.insert(word);
            //word = new Word("World");
            //mDao.insert(word);
            return null;
        }
    }

    public abstract RecipeDao recipeDao();

}

