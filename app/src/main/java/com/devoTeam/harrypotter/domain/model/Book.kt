package com.devoTeam.harrypotter.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "itemCart")
data class Book(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("isbn") var isbn: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("price") var price: String = "",
    @SerializedName("cover") var cover: String = "",
    @Ignore
    @SerializedName("synopsis") var synopsis: List<String> = arrayListOf()

):Serializable