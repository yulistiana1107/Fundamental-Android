package com.project.githubuserproject.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
        entities = [Favorite::class],
        version = 8
)
abstract class FavoriteDatabase : RoomDatabase() {
    companion object{
        var YULIS_DATABASE : FavoriteDatabase? = null

        fun Database(context: Context): FavoriteDatabase?{
            if (YULIS_DATABASE==null){
                synchronized(FavoriteDatabase::class){
                    YULIS_DATABASE = Room.databaseBuilder(context.applicationContext, FavoriteDatabase::class.java, "favorite_database")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return YULIS_DATABASE
        }
    }
    abstract fun favoriteDao(): FavoriteDao
}