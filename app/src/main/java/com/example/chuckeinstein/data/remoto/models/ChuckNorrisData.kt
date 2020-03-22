package com.example.chuckeinstein.data.remoto.models

import com.squareup.moshi.Json

class ChuckNorrisData(
    @Json(name = "total")
    var qtdadeTotalItens: Int?,
    @Json(name = "result")
    var detalhesPiada: List<DetalhesPiada>?
)