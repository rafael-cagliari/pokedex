package com.example.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokedex.databinding.PokemonDetailsFragmentBinding
import com.squareup.picasso.Picasso


class PokemonDetailsFragment: Fragment() {
    val args: PokemonDetailsFragmentArgs by navArgs()
    private lateinit var letterId: String
    var _binding:PokemonDetailsFragmentBinding? = null
    val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PokemonDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = args.message.toString()
        binding.pokemonDetailImage

        Picasso.get()
            .load("${args.message}")
            .resize(150, 150)
            .into(binding.pokemonDetailImage)
        binding.pokemonDetailImage.run {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                clipToOutline = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}