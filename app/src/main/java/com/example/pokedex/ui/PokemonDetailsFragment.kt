package com.example.pokedex.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.databinding.PokemonDetailsFragmentBinding
import com.example.pokedex.databinding.PokemonListFragmentBinding
import com.squareup.picasso.Picasso
import java.io.IOException

class PokemonDetailsFragment : Fragment() {

    val args: PokemonDetailsFragmentArgs by navArgs()
    var _binding: PokemonDetailsFragmentBinding? = null
    val binding get() = _binding!!

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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.pokemonDetailImage
        binding.detailsName.text = args.name
        binding.detailsWeight.text = args.wight
        binding.detailsHeight.text = args.height
        binding.detailsNum.text = args.number
        binding.detailsCandy.text = args.candy
        binding.typeDetail1.setImageDrawable(typeSelector(args.type[0]))
        if (args.type.size > 1) {
            binding.typeDetail2.setImageDrawable(typeSelector(args.type[1]))
        } else {
            binding.typeDetail2.setImageDrawable(null)
        }
        binding.weaknessDetail1.setImageDrawable(typeSelector(args.weaknesses[0]))
        if (args.weaknesses.size > 1) {
            binding.weaknessDetail2.setImageDrawable(typeSelector(args.weaknesses[1]))
        }
        if (args.weaknesses.size > 2) {
            binding.weaknessDetail3.setImageDrawable(typeSelector(args.weaknesses[2]))
        }
        if (args.weaknesses.size > 3) {
            binding.weaknessDetail4.setImageDrawable(typeSelector(args.weaknesses[3]))
        }
        if (args.weaknesses.size > 4) {
            binding.weaknessDetail5.setImageDrawable(typeSelector(args.weaknesses[4]))
        }
        if (args.weaknesses.size > 5) {
            binding.weaknessDetail6.setImageDrawable(typeSelector(args.weaknesses[5]))
        }
        if (args.weaknesses.size > 6) {
            binding.weaknessDetail7.setImageDrawable(typeSelector(args.weaknesses[6]))
        }


        Picasso.get()
            .load("https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${args.nameLowerCase}.png")
            .resize(150, 150)
            .into(binding.pokemonDetailImage)
        binding.pokemonDetailImage.run {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                clipToOutline = true
            }
        }
        val url =
            "https://play.pokemonshowdown.com/audio/cries/${args.nameLowerCase}.mp3" // your URL here
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
            start()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun typeSelector(type: String?): Drawable? {

        when (type) {

            "Rock" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.rock) }
            }
            "Bug" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.bug) }
            }
            "Ground" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.ground) }
            }
            "Fire" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.fire) }
            }
            "Water" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.water) }
            }
            "Ghost" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.ghost) }
            }
            "Dragon" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.dragon) }
            }
            "Psychic" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.psychc) }
            }
            "Electric" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.electr) }
            }
            "Fighting" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.fight) }
            }
            "Flying" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.flying) }
            }
            "Grass" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.grass) }
            }
            "Normal" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.normal) }
            }
            "Poison" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.poison) }
            }
            "Ice" -> {
                return context?.let { ContextCompat.getDrawable(it, R.drawable.ice) }
            }
            else -> return null
        }

    }
}
