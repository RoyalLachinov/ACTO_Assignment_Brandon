package com.project.acto_assignment_brandon.OffLineDataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {User.class , Album.class, Photo.class}, version = 3, exportSchema = false)


public abstract class Db extends RoomDatabase {

    private static Db dbInstance;

    public abstract UserDao userDao();
    public abstract AlbumDao albumDao();
    public abstract PhotoDao photoDao();


    public static synchronized Db getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    Db.class, "table_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callBack).build();
        }

        return dbInstance;
    }
    private static RoomDatabase.Callback callBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };


}
