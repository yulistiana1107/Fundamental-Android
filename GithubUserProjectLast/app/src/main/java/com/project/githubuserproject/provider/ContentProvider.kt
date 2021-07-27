package com.project.githubuserproject.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.project.githubuserproject.model.local.FavoriteDao
import com.project.githubuserproject.model.local.FavoriteDatabase

class ContentProvider : ContentProvider() {

    companion object {
        const val YULIS_AUTHORITY = "com.project.githubuserproject"
        const val YULIS_TABLE = "favorite_user"
        const val YULIS_ID_USER = 1
        val uriMatchYulis = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatchYulis.apply {
                addURI(YULIS_AUTHORITY, YULIS_TABLE, YULIS_ID_USER) }
        }
    }

    private lateinit var yulisfavoritedao: FavoriteDao

    override fun onCreate(): Boolean {
        yulisfavoritedao = context?.let {
            FavoriteDatabase.Database(it)?.favoriteDao()
        }!!

        return false
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        var cursor: Cursor?
        when (uriMatchYulis.match(uri)) {
            YULIS_ID_USER -> {
                cursor = yulisfavoritedao.find()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            } else -> {
            cursor = null
        }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
       return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        return 0
    }
}