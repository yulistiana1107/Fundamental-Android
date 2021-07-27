package com.project.githubuserproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.githubuserproject.model.local.Favorite
import com.project.githubuserproject.model.local.FavoriteDao
import com.project.githubuserproject.model.local.FavoriteDatabase

class FavoriteViewModel (application: Application): AndroidViewModel(application) {
    private var yulisfavoritedao: FavoriteDao?
    private var yulisfavoriteDb: FavoriteDatabase?

    init {
        yulisfavoriteDb = FavoriteDatabase.Database(application)
        yulisfavoritedao = yulisfavoriteDb?.favoriteDao()
    }

    fun getFavorite(): LiveData<List<Favorite>>?{
        return yulisfavoritedao?.getFavorite()
    }
}