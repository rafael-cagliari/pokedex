package com.example.pokedex.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.PokemonListFragmentDirections
import com.example.pokedex.utils.TypeColor.typeColor
import com.example.pokedex.utils.TypeSelector.typeSelector
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class PokemonAdapter(private val pokemonList: List<Pokemon>) :

    RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>(), Filterable {

    /*filtering for pokemon searching bar, the recycler view will always return the filtering results,
    * except for when there is no search, returning the whole list */

    var pokemonFilterList = pokemonList.toMutableList()
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                pokemonFilterList = if (charSearch.isEmpty()) {
                    pokemonList.toMutableList()
                } else {
                    val resultList = ArrayList<Pokemon>()
                    for (row in pokemonList) {
                        if (row.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT)) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
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

        //changes the background color for the item based on its primary type
        typeColor(holder.itemView.context, pokemon.type?.get(0))
            ?.let { holder.pokemonImage.setBackgroundResource(it) }

        //loads the pokemon image
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

        //applies the drawables for pokemon type
        holder.pokemonType1.setImageDrawable(
            typeSelector(
                holder.itemView.context,
                pokemon.type?.get(0)
            )
        )
        if (pokemon.type?.size!! > 1) {
            holder.pokemonType2.setImageDrawable(
                typeSelector(
                    holder.itemView.context,
                    pokemon.type[1]
                )
            )
        } else {
            holder.pokemonType2.setImageDrawable(null)
        }

        /* clicking a pokemon on the list navigates to PokemonDetailsFragment, passing along
        the pokemon's properties as arguments
        */
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

                    /*passes all of the next and previous evolutions as an array
                    containing the name and level for that evolution, passing a "null" string in case of a null parameter
                    ex.: ["raichu", "16", "none", "none"]
                    */
                    nextEvolution = arrayOf(
                        pokemon.nextEvolution?.getOrNull(0)?.name.toString(),
                        pokemon.nextEvolution?.getOrNull(0)?.evoLevel.toString(),
                        pokemon.nextEvolution?.getOrNull(1)?.name.toString(),
                        pokemon.nextEvolution?.getOrNull(1)?.evoLevel.toString()
                    ),

                    prevEvolution = arrayOf(
                        pokemon.prevEvolution?.getOrNull(0)?.name.toString(),
                        pokemon.prevEvolution?.getOrNull(0)?.evoLevel.toString(),
                        pokemon.prevEvolution?.getOrNull(1)?.name.toString(),
                        pokemon.prevEvolution?.getOrNull(1)?.evoLevel.toString()
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
    }
}