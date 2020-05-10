package br.com.antoniocgrande.chucknoia.data.model

import com.google.gson.annotations.SerializedName

/* Copyright 2020.
 ************************************************************
 * Project     : ChuckNoia
 * Description : br.com.antoniocgrande.chucknoia.data.model
 * Created by  : antoniocgrande
 * Date        : 10/05/2020 02:57
 ************************************************************/
data class Joke(
    @SerializedName("categories") var categories: List<String>,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("icon_url") var iconUrl: String,
    @SerializedName("id") var id: String,
    @SerializedName("updated_at") var updatedAt: String,
    @SerializedName("url") var url: String,
    @SerializedName("value") var value: String
)