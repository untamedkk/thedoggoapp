package com.doogosearch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Search(
    @SerializedName("bred_for") val bredFor: String,
    @SerializedName("breed_group") val breedGroup: String,
    @SerializedName("life_span") val lifeSpan: String, val height: Height, val id: Int,
    val name: String,
    val temperament: String,
) : BaseSearch, Serializable