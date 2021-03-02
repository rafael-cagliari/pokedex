package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.PokemonListFragment
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.pokemon_name)
        val imageView: ImageView = view.findViewById(R.id.pokemon_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.textView.text = pokemon.name
        Picasso.get()
            .load("${pokemon.img}")
            .resize(150, 150)
            .into(holder.imageView)
        holder.imageView.run {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                clipToOutline = true
            }

        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}