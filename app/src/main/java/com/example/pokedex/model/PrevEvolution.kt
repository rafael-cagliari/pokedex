package com.example.pokedex.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class PrevEvolution {
    @SerializedName("num")
    @Expose
    val num: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null
}