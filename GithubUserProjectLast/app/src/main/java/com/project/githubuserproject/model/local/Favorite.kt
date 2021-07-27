package com.project.githubuserproject.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_user")
data class Favorite (
        val name: String,
        @PrimaryKey
        val id: Int,
        val photo: String,
        val detail: String,
        var followers: String,
        var following: String,
        var workplace: String,
        var location: String,
        var repositories: String
): Serializable