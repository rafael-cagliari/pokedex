package com.example.pokedex.model
import com.google.gson.annotations.SerializedName


data class PokemonResponse (
    @SerializedName("pokemon")
    val pokemons: List<Pokemon>? = null
)