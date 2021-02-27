package com.example.pokedex.data

import com.example.pokedex.R
import com.example.pokedex.model.Pokemon


class Datasource() {


//Creates a list of Pokemon objects

    fun loadPokemon(): List<Pokemon>{
        return listOf<Pokemon>(
                Pokemon(R.string.bulbassaur, R.drawable._01),
                Pokemon(R.string.ivyssaur, R.drawable._02),
                Pokemon(R.string.venossaur, R.drawable._03),
                Pokemon(R.string.charmander, R.drawable._04),
                Pokemon(R.string.charmeleon, R.drawable._05),
                Pokemon(R.string.charizard, R.drawable._06),
                Pokemon(R.string.squirtle, R.drawable._07),
                Pokemon(R.string.wartotle, R.drawable._08),
                Pokemon(R.string.blastoise, R.drawable._09)
        )
    }
}