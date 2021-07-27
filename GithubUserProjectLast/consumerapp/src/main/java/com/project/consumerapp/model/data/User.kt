package com.project.consumerapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
        var name: String? = null,
        var detail: String? = null,
        var photo: String?= null,
        var id: Int?= null,
        var followers: String?= null,
        var following: String?= null,
        var workplace: String?= null,
        var location: String?= null,
        var repositories: String?= null
) : Parcelable