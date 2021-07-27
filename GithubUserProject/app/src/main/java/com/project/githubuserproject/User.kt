package com.project.githubuserproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    var name: String? = null,
    var detail: String? = null,
    var following: String?= null,
    var followers: String?= null,
    var workplace: String?= null,
    var location: String?= null,
    var photo: String?= null,
    var repositories: String?= null
) : Parcelable