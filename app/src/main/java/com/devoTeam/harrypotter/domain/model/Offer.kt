package com.devoTeam.harrypotter.domain.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Offer(
    @SerializedName("type") var type: String = "",
    @SerializedName("value") var value: String = "",
    @SerializedName("sliceValue") var sliceValue: String = "",
) : Serializable