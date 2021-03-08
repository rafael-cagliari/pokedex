package com.example.pokedex.adapter

import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.pokemonName.text = pokemon.name
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
        if(pokemon.type?.size!! > 1){ holder.pokemonType2.setImageDrawable(holder.typeSelector(pokemon.type?.get(1)))}
        else {holder.pokemonType2.setImageDrawable(null)}
        holder.pokemonImage.setOnClickListener{
            val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(message = "https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${pokemon.name.toString().toLowerCase()}.png"!!,
                cries = pokemon.name.toString().toLowerCase())
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return pokemonList.size
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

    }
}