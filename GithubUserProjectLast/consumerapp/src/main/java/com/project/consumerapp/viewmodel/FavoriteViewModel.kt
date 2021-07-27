package com.project.consumerapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.consumerapp.model.data.User
import com.project.consumerapp.model.local.DatabaseContract
import com.project.consumerapp.model.local.MappingHelper

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var yulislist = MutableLiveData<ArrayList<User>>()

    fun yulisFavoriteUser(context: Context) {
        val cursor = context.contentResolver.query(
                DatabaseContract.FavoriteUserColumns.YULIS_CONTENT_URI,
                null,
                null,
                null,
                null
        )
        val yulisListConverted = MappingHelper.mapCursorToArrayList(cursor)
        yulislist.postValue(yulisListConverted)

    }

    fun yulisFavorite(): LiveData<ArrayList<User>>? {
        return yulislist
    }
}