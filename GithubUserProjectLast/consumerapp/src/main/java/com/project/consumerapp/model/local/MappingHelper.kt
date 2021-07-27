package com.project.consumerapp.model.local

import android.database.Cursor
import com.project.consumerapp.model.data.User

object MappingHelper {

    fun mapCursorToArrayList(yulis: Cursor?): ArrayList<User> {
        val yulisList = ArrayList<User>()
        if (yulis != null) {
            while (yulis.moveToNext()) {
                val yulisid = yulis.getInt(yulis.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.YULIS_ID))
                val yulisusername = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_USERNAME))
                val yulisavatarUrl = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_AVATAR_URL))
                val yulisdetail = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_DETAIL))
                val yulisfollowers = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_FOLLOWERS))
                val yulisfollowing = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_FOLLOWING))
                val yulisworkplace = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_WORKPLACE))
                val yulislocation = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_LOCATION))
                val yulisrepositories = yulis.getString(yulis.getColumnIndex(DatabaseContract.FavoriteUserColumns.YULIS_REPOSITORIES))

                yulisList.add(
                        User(
                                yulisusername,
                                yulisdetail,
                                yulisavatarUrl,
                                yulisid,
                                yulisfollowers,
                                yulisfollowing,
                                yulisworkplace,
                                yulislocation,
                                yulisrepositories
                        )
                )
            }
        }
        return yulisList
    }
}