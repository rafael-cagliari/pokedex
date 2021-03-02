package com.example.pokedex.model
import com.google.gson.annotations.SerializedName


data class Pokemon(
    @SerializedName("id")

     val id: Int? = null,

    @SerializedName("num")

     val num: String? = null,

    @SerializedName("name")

     val name: String? = null,

    @SerializedName("img")

     val img: String? = null,

    @SerializedName("type")

     val type: List<String>? = null,

    @SerializedName("height")

     val height: String? = null,

    @SerializedName("weight")

     val weight: String? = null,
    @SerializedName("candy")

     val candy: String? = null,

    @SerializedName("candy_count")

     val candyCount: Int? = null,

    @SerializedName("egg")

     val egg: String? = null,

    @SerializedName("spawn_chance")

     val spawnChance: Double? = null,

    @SerializedName("avg_spawns")

     val avgSpawns: Double? = null,

    @SerializedName("spawn_time")

     val spawnTime: String? = null,

    @SerializedName("multipliers")

     val multipliers: Any? = null,

    @SerializedName("weaknesses")

    val weaknesses: List<String>? = null
)
