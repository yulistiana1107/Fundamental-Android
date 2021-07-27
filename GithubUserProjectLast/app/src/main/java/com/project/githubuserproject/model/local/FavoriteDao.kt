package com.project.githubuserproject.model.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_user")
    fun getFavorite(): LiveData<List<Favorite>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id= :id")
    suspend fun check(id: Int): Int

    @Query( "DELETE FROM favorite_user WHERE favorite_user.id= :id")
    suspend fun removeFavorite(id: Int): Int

    @Query("SELECT * FROM favorite_user")
    fun find(): Cursor
}