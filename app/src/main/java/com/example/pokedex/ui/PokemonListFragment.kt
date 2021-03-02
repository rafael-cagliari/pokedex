package com.example.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.data.Datasource
import com.example.pokedex.databinding.PokemonListFragmentBinding
import com.squareup.picasso.Picasso

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
        recyclerView.adapter = PokemonAdapter(pokemonData.pokemons ?: listOf())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PokemonListFragment()
    }
}
