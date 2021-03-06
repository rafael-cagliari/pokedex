package com.example.pokedex.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class NextEvolution {
    @SerializedName("num")
    @Expose
    val evoLevel: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null
}