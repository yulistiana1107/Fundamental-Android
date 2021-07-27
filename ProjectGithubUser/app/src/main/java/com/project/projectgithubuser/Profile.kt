package com.project.projectgithubuser

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Profile (
    var name: String? = "",
    var detail: String = "",
    var pekerjaan: String ="",
    var following: String="",
    var followers: String="",
    var tempatkerja: String="",
    var lokasi: String="",
    var photo: Int = 0
) : Parcelable