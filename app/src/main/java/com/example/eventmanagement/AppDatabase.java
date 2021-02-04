package com.example.eventmanagement;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database
        (entities = {UserEntity.class, EventEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract EventDao eventDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    //create our db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "user_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
