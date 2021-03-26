package com.example.pokedex.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.PokemonDetailsFragmentBinding
import com.squareup.picasso.Picasso


@Suppress("UNREACHABLE_CODE")
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

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var firstType = args.type[0]
        typeColor(firstType)?.let { binding.evoBox.setBackgroundResource(it) }
        binding.pokemonDetailImage
        binding.detailsName.text = args.name
        binding.detailsWeight.text = args.wight
        binding.detailsHeight.text = args.height
        binding.detailsNum.text = args.number
        binding.description.text = args.description
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

        // if and else if testing 6 different conditions to establish in which evolution stage
        // pokemon is and modify "evolution" section

        Log.d("nextevolution checker", args.nextEvolution?.get(2).toString())
        //pokemon is the first in a 3 pokemon evolution tree
        if (args.nextEvolution?.get(2)!! != "null") {
            binding.evo1.text = args.name
            binding.evo2.text = args.nextEvolution?.get(0)
            binding.evo3.text = args.nextEvolution?.get(2)
            binding.evoCond1.text = args.nextEvolution?.get(1)
            binding.evoCond2.text = args.nextEvolution?.get(3)
        }

        //pokemon is the second in a 3 pokemon evolution tree
        else if (args.nextEvolution?.get(0)!! != "null" && args.prevEvolution?.get(0)!! != "null") {
            binding.evo1.text = args.prevEvolution?.get(0)
            binding.evo2.text = args.name
            binding.evo3.text = args.nextEvolution?.get(0)
            binding.evoCond1.text = args.prevEvolution?.get(1)
            binding.evoCond2.text = args.nextEvolution?.get(1)
        }

        //pokemon is the third in a 3 pokemon evolution tree
        else if (args.prevEvolution?.get(2)!! != "null") {
            binding.evo1.text = args.prevEvolution?.get(0)
            binding.evo2.text = args.prevEvolution?.get(2)
            binding.evo3.text = args.name
            binding.evoCond1.text = args.prevEvolution?.get(1)
            binding.evoCond2.text = args.prevEvolution?.get(3)
        }

        //pokemon is the first of a 2 pokemon evolution tree
        else if (args.nextEvolution?.get(1)!! != "null" && args.prevEvolution!!.get(0)!! == "null") {
            binding.evo1.text = args.name
            binding.evo2.text = args.nextEvolution?.get(0)
            binding.evoCond1.text = args.nextEvolution?.get(1)
            binding.evo3.visibility = View.GONE
            binding.evoCond2.visibility = View.GONE
        }

        //pokemon is the second of a 2 pokemon evolution tree
        else if (args.prevEvolution?.get(1)!! != "null" && args.nextEvolution!!.get(0)!! == "null") {
            binding.evo1.text = args.prevEvolution?.get(0)
            binding.evo2.text = args.name
            binding.evoCond1.text = args.prevEvolution?.get(1)
            binding.evo3.visibility = View.GONE
            binding.evoCond2.visibility = View.GONE
        } else {
            binding.cardView.visibility = View.INVISIBLE
        }


        // adds pokemon pictures to the evolution tree
        Picasso.get()
            .load(
                "https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${
                    binding.evo1.text.toString().toLowerCase()
                }.png"
            )
            .resize(170, 140)
            .into(binding.evoFigure1)


        Picasso.get()
            .load(
                "https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${
                    binding.evo2.text.toString().toLowerCase()
                }.png"
            )
            .resize(170, 140)
            .into(binding.evoFigure2)

        if (binding.evo3.text.isNotBlank()) {
            Picasso.get()
                .load(
                    "https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${
                        binding.evo3.text.toString().toLowerCase()
                    }.png"
                )
                .resize(170, 140)
                .into(binding.evoFigure3)
        } else null


        // adds main pokemon picture

        Picasso.get()

            .load(
                "https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${
                    args.nameLowerCase.replace(
                        "'",
                        ""
                    ).replace(". ", "-")
                }.png"
            )
            .resize(150, 150)

            .into(binding.pokemonDetailImage)

        val url =
            "https://play.pokemonshowdown.com/audio/cries/${
                args.nameLowerCase.replace("-", "").replace("'", "").replace(". ", "")
            }.mp3" // your URL here
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
