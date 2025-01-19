package com.moobaalabs.omnifilms.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Actors") val actors: String? = "Atores não disponíveis",
    @SerializedName("Awards") val awards: String? = "Prêmios não disponíveis",
    @SerializedName("BoxOffice") val boxOffice: String? = "Bilheteira não disponível",
    @SerializedName("Country") val country: String? = "País não disponível",
    @SerializedName("DVD") val dvd: String? = "DVD não disponível",
    @SerializedName("Director") val director: String? = "Diretor não disponível",
    @SerializedName("Genre") val genre: String? = "Gênero não disponível",
    @SerializedName("Language") val language: String? = "Idioma não disponível",
    @SerializedName("Metascore") val metascore: String? = "Metascore não disponível",
    @SerializedName("Plot") val plot: String? = "Sinopse não disponível",
    @SerializedName("Poster") val poster: String? = "Imagem não disponível",
    @SerializedName("Production") val production: String? = "Produção não disponível",
    @SerializedName("Rated") val rated: String? = "Classificação não disponível",
    @SerializedName("Released") val released: String? = "Data de lançamento não disponível",
    @SerializedName("Response") val response: String? = "Resposta não disponível",
    @SerializedName("Runtime") val runtime: String? = "Duração não disponível",
    @SerializedName("Title") val title: String? = "Título não disponível",
    @SerializedName("Type") val type: String? = "Tipo não disponível",
    @SerializedName("Website") val website: String? = "Website não disponível",
    @SerializedName("Writer") val writer: String? = "Escritor não disponível",
    @SerializedName("Year") val year: String? = "Ano não disponível",
    @SerializedName("imdbID") val imdbID: String? = "ID do IMDb não disponível",
    @SerializedName("imdbRating") val imdbRating: String? = "Classificação IMDb não disponível",
    @SerializedName("imdbVotes") val imdbVotes: String? = "Votos IMDb não disponíveis"
)
