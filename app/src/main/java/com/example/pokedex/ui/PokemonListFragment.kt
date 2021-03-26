package com.example.pokedex.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.data.Datasource
import com.example.pokedex.databinding.PokemonListFragmentBinding
import com.example.pokedex.model.Pokemon

class PokemonListFragment : Fragment() {
    private var _binding: PokemonListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PokemonListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pokemonData = Datasource().gsonFromJson(requireContext(), "response.json")
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val adapter = PokemonAdapter(pokemonData.pokemons ?: listOf())
        recyclerView.adapter = adapter

        binding.pokemonSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PokemonListFragment()
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
