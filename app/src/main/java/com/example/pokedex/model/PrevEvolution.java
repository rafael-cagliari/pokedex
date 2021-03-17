package com.example.pokedex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrevEvolution {
    @SerializedName("num")
    @Expose
    private String num;
    @SerializedName("name")
    @Expose
    private String name;
}

