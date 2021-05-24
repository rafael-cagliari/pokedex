package com.example.pokedex.ui

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokedex.databinding.PokemonDetailsFragmentBinding
import com.example.pokedex.utils.TypeColor.typeColor
import com.example.pokedex.utils.TypeSelector.typeSelector
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


@Suppress("UNREACHABLE_CODE")
class PokemonDetailsFragment : Fragment() {


    private val args: PokemonDetailsFragmentArgs by navArgs()
    private var _binding: PokemonDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PokemonDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonDetailImage

        binding.detailsName.text = args.name

        binding.detailsWeight.text = args.wight

        binding.detailsHeight.text = args.height

        binding.detailsNum.text = args.number

        binding.description.text = args.description

        //sets drawable for pokemon type
        binding.typeDetail1.setImageDrawable(typeSelector(requireContext(), args.type[0]))
        if (args.type.size > 1) {
            binding.typeDetail2.setImageDrawable(typeSelector(requireContext(), args.type[1]))
        } else {
            binding.typeDetail2.setImageDrawable(null)
        }

        //sets drawable for pokemon weaknesses
        binding.weaknessDetail1.setImageDrawable(typeSelector(requireContext(), args.weaknesses[0]))
        if (args.weaknesses.size > 1) {
            binding.weaknessDetail2.setImageDrawable(
                typeSelector(
                    requireContext(),
                    args.weaknesses[1]
                )
            )
        }
        if (args.weaknesses.size > 2) {
            binding.weaknessDetail3.setImageDrawable(
                typeSelector(
                    requireContext(),
                    args.weaknesses[2]
                )
            )
        }
        if (args.weaknesses.size > 3) {
            binding.weaknessDetail4.setImageDrawable(
                typeSelector(
                    requireContext(),
                    args.weaknesses[3]
                )
            )
        }
        if (args.weaknesses.size > 4) {
            binding.weaknessDetail5.setImageDrawable(
                typeSelector(
                    requireContext(),
                    args.weaknesses[4]
                )
            )
        }
        if (args.weaknesses.size > 5) {
            binding.weaknessDetail6.setImageDrawable(
                typeSelector(
                    requireContext(),
                    args.weaknesses[5]
                )
            )
        }
        if (args.weaknesses.size > 6) {
            binding.weaknessDetail7.setImageDrawable(
                typeSelector(
                    requireContext(),
                    args.weaknesses[6]
                )
            )
        }

        //sets the background color of the evolution tree box based on the primary type of the pokemon
        val firstType = args.type[0]
        typeColor(requireContext(), firstType)?.let { binding.evoBox.setBackgroundResource(it) }


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
        else if (args.nextEvolution?.get(1)!! != "null" && args.prevEvolution!![0] == "null") {
            binding.evo1.text = args.name
            binding.evo2.text = args.nextEvolution?.get(0)
            binding.evoCond1.text = args.nextEvolution?.get(1)
            binding.evo3.visibility = View.GONE
            binding.evoCond2.visibility = View.GONE
        }

        //pokemon is the second of a 2 pokemon evolution tree
        else if (args.prevEvolution?.get(1)!! != "null" && args.nextEvolution!![0] == "null") {
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
            .into(binding.evoFigure2, object : Callback{
                override fun onSuccess() {
                    binding.evoBox.visibility=View.VISIBLE
                }
                override fun onError(e: Exception?) {
                   Log.d("Image loading", "failed")
                }
            })


        if (binding.evo3.text.isNotBlank()) {
            Picasso.get()
                .load(
                    "https://img.pokemondb.net/sprites/lets-go-pikachu-eevee/normal/${
                        binding.evo3.text.toString().toLowerCase()
                    }.png"
                )
                .resize(170, 140)
                .into(binding.evoFigure3)
        }


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

        // adds pokemon cry
        val url =
            "https://play.pokemonshowdown.com/audio/cries/${
                args.nameLowerCase.replace("-", "").replace("'", "").replace(". ", "")
            }.mp3" // your URL here
        MediaPlayer().apply {
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
}
