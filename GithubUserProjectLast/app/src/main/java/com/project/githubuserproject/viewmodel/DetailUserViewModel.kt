package com.project.githubuserproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.project.githubuserproject.model.local.Favorite
import com.project.githubuserproject.model.local.FavoriteDao
import com.project.githubuserproject.model.local.FavoriteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserViewModel (application: Application) : AndroidViewModel(application) {

    private var yulisdao: FavoriteDao?
    private var yulisfavoriteDb: FavoriteDatabase?

    init {
        yulisfavoriteDb = FavoriteDatabase.Database(application)
        yulisdao = yulisfavoriteDb?.favoriteDao()
    }
    fun addFavorite(username: String, id:Int, avatarUrl: String, fullName: String, following: String, followers: String, workplace: String, location: String, repositories: String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = Favorite(
                    username,
                    id,
                    avatarUrl,
                    fullName,
                    following,
                    followers,
                    workplace,
                    location,
                    repositories
            )
            yulisdao?.addFavorite(user)
        }
    }

    suspend fun check(id: Int) = yulisdao?.check(id)

    fun removeFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            yulisdao?.removeFavorite(id)
        }
    }

}