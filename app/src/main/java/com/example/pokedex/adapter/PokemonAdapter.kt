package com.example.pokedex.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.PokemonListFragmentDirections
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class PokemonAdapter(private val pokemonList: List<Pokemon>) :

    RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>(), Filterable {
    var pokemonFilterList = pokemonList.toMutableList()
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    pokemonFilterList = pokemonList.toMutableList()
                } else {
                    val resultList = ArrayList<Pokemon>()
                    for (row in pokemonList) {
                        if (row.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT)) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    pokemonFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = pokemonFilterList

                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                pokemonFilterList = results?.values as MutableList<Pokemon>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pokemon = pokemonFilterList[position]
        holder.pokemonName.text = pokemon.name
        holder.typeColor(pokemon.type?.get(0))
            ?.let { holder.pokemonImage.setBackgroundResource(it) }
        Picasso.get()
            .load("${pokemon.img}")
            .resize(150, 150)
            .into(holder.pokemonImage)
        holder.pokemonImage.run {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                clipToOutline = true
            }
        }
        holder.pokemonId.text = "Nº: " + pokemon.id
        holder.pokemonType1.setImageDrawable(holder.typeSelector(pokemon.type?.get(0)))
        if (pokemon.type?.size!! > 1) {
            holder.pokemonType2.setImageDrawable(holder.typeSelector(pokemon.type?.get(1)))
        } else {
            holder.pokemonType2.setImageDrawable(null)
        }
        holder.pokemonImage.setOnClickListener {
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                    description = pokemon.desc!!,
                    name = pokemon.name!!,
                    nameLowerCase = pokemon.name.toLowerCase(),
                    number = pokemon.num!!,
                    height = pokemon.height!!,
                    wight = pokemon.weight!!,
                    type = pokemon.type.toTypedArray(),
                    weaknesses = pokemon.weaknesses!!.toTypedArray(),
                    nextEvolution = arrayOf(
                        pokemon.nextEvolution?.getOrNull(0)?.name.toString(),
                        pokemon.nextEvolution?.getOrNull(0)?.num.toString(),
                        pokemon.nextEvolution?.getOrNull(1)?.name.toString(),
                        pokemon.nextEvolution?.getOrNull(1)?.num.toString()
                    ),

                    prevEvolution = arrayOf(
                        pokemon.prevEvolution?.getOrNull(0)?.name.toString(),
                        pokemon.prevEvolution?.getOrNull(0)?.num.toString(),
                        pokemon.prevEvolution?.getOrNull(1)?.name.toString(),
                        pokemon.prevEvolution?.getOrNull(1)?.num.toString()
                    ),

                    )
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return pokemonFilterList.size

    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pokemonName: TextView = view.findViewById(R.id.pokemon_name)
        val pokemonImage: ImageView = view.findViewById(R.id.pokemon_image)
        val pokemonId: TextView = view.findViewById(R.id.pokemon_id)
        val pokemonType1: ImageView = view.findViewById(R.id.type1)
        val pokemonType2: ImageView = view.findViewById(R.id.type2)


        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun typeSelector(type: String?): Drawable? {

            when (type) {

                "Rock" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.rock)
                }
                "Bug" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.bug)
                }
                "Ground" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.ground)
                }
                "Fire" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.fire)
                }
                "Water" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.water)
                }
                "Ghost" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.ghost)
                }
                "Dragon" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.dragon)
                }
                "Psychic" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.psychc)
                }
                "Electric" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.electr)
                }
                "Fighting" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.fight)
                }
                "Flying" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.flying)
                }
                "Grass" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.grass)
                }
                "Normal" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.normal)
                }
                "Poison" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.poison)
                }
                "Ice" -> {
                    return ContextCompat.getDrawable(view.context, R.drawable.ice)
                }
                else -> return null
            }

        }

        @SuppressLint("ResourceAsColor")
        fun typeColor(type: String?): Int? {

            when (type) {

                "Rock" -> {
                    return R.color.rock
                }
                "Bug" -> {
                    return R.color.bug
                }
                "Ground" -> {
                    return R.color.ground
                }
                "Fire" -> {
                    return R.color.fire
                }
                "Water" -> {
                    return R.color.water
                }
                "Ghost" -> {
                    return R.color.ghost
                }
                "Dragon" -> {
                    return R.color.dragon
                }
                "Psychic" -> {
                    return R.color.psychic
                }
                "Electric" -> {
                    return R.color.electric
                }
                "Fighting" -> {
                    return R.color.fight
                }
                "Flying" -> {
                    return R.color.flying
                }
                "Grass" -> {
                    return R.color.grass
                }
                "Normal" -> {
                    return R.color.normal
                }
                "Poison" -> {
                    return R.color.poison
                }
                "Ice" -> {
                    return R.color.ice
                }
                else -> return null
            }
        }


    }
}