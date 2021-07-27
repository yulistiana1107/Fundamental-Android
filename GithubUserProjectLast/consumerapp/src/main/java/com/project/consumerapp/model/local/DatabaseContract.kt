package com.project.consumerapp.model.local

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val YULIS_AUTHORITY = "com.project.githubuserproject"
    const val YULIS_SCHEME = "content"

    internal class FavoriteUserColumns: BaseColumns{
        companion object{
            const val YULIS_TABLE_NAME ="favorite_user"
            const val YULIS_ID ="id"
            const val YULIS_USERNAME ="name"
            const val YULIS_AVATAR_URL ="photo"
            const val YULIS_DETAIL ="detail"
            const val YULIS_FOLLOWERS ="followers"
            const val YULIS_FOLLOWING ="following"
            const val YULIS_WORKPLACE = "workplace"
            const val YULIS_LOCATION ="location"
            const val YULIS_REPOSITORIES ="repositories"

            val YULIS_CONTENT_URI = Uri.Builder().scheme(YULIS_SCHEME)
                    .authority(YULIS_AUTHORITY)
                    .appendPath(YULIS_TABLE_NAME)
                    .build()
        }
    }
}
