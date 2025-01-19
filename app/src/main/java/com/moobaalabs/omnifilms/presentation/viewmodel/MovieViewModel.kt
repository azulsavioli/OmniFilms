package com.moobaalabs.omnifilms.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.data.remote.response.MovieResponse
import com.moobaalabs.omnifilms.domain.usecase.*
import com.moobaalabs.omnifilms.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val saveRecentlyViewedMovieUseCase: SaveRecentlyViewedMovieUseCase,
    private val getRecentlyViewedMoviesUseCase: GetRecentlyViewedMoviesUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
    private val removeRecentlyViewedMovieUseCase: RemoveSearchedMovieUseCase
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<MovieResponse>>(emptyList())
    val searchResults: StateFlow<List<MovieResponse>> get() = _searchResults

    private val _recentlyViewed = MutableStateFlow<List<MovieEntity>>(emptyList())
    val recentlyViewed: StateFlow<List<MovieEntity>> get() = _recentlyViewed

    private val _favorites = MutableStateFlow<List<MovieEntity>>(emptyList())
    val favorites: StateFlow<List<MovieEntity>> get() = _favorites

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun searchMovie(query: String, context: Context) {
        if (query.isEmpty()) return
        if (query.trim().length < 3) {
            Toast.makeText(
                context,
                "A consulta deve ter pelo menos 3 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val formattedQuery = query.trim().replace(" ", "+")

        viewModelScope.launch {
            _isLoading.value = true

            when (val result = searchMoviesUseCase.execute(formattedQuery)) {
                is Resource.Success -> {
                    if (result.data?.response.isNullOrEmpty() || result.data?.response == "False") {
                        Toast.makeText(
                            context,
                            "Nenhum resultado encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                        _isLoading.value = false
                        return@launch
                    }
                    result.data?.let { movieResponse ->
                        _searchResults.value = _searchResults.value + movieResponse
                        val movieEntity = convertToEntity(movieResponse)
                        saveRecentlyViewedMovie(movieEntity)
                    }
                }

                is Resource.Error -> {
                    val message = when {
                        result.message == "No Internet Connection" -> "Sem conexão com a internet"
                        result.message == "Invalid query format" -> "Formato de consulta inválido"
                        result.message?.contains("timeout") == true -> "Erro de tempo limite"
                        else -> "Ocorreu um erro inesperado"
                    }
                    Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                    getRecentlyViewedMovies()
                    _isLoading.value = false
                    return@launch
                }
            }
            getRecentlyViewedMovies()
            _isLoading.value = false
        }
    }

    private fun saveRecentlyViewedMovie(movie: MovieEntity) {
        viewModelScope.launch {
            saveRecentlyViewedMovieUseCase.execute(movie)
            getRecentlyViewedMovies()
        }
    }

    fun getRecentlyViewedMovies() {
        viewModelScope.launch {
            val movies = getRecentlyViewedMoviesUseCase.execute()
            _recentlyViewed.value = movies
        }
    }

    fun removeRecentlyViewedMovie(movie: MovieEntity) {
        viewModelScope.launch {
            removeRecentlyViewedMovieUseCase.execute(movie)
            getRecentlyViewedMovies()
        }
    }

    fun toggleFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                removeFavoriteMovieUseCase.execute(movie)
            } else {
                saveFavoriteMovieUseCase.execute(movie)
            }
            getFavoriteMovies()
            getRecentlyViewedMovies()
        }
    }

    fun getFavoriteMovies() {
        viewModelScope.launch {
            val movies = getFavoriteMoviesUseCase.execute()
            _favorites.value = movies
        }
    }

    private fun convertToEntity(movieResponse: MovieResponse): MovieEntity {
        return MovieEntity(
            title = movieResponse.title ?: "Título não disponível",
            year = movieResponse.year ?: "Ano não disponível",
            director = movieResponse.director ?: "Diretor não disponível",
            poster = movieResponse.poster ?: "Imagem não disponível",
            genre = movieResponse.genre ?: "Gênero não disponível",
            language = movieResponse.language ?: "Idioma não disponível",
            imdbId = movieResponse.imdbID ?: "ID do IMDb não disponível",
            rated = movieResponse.rated ?: "Classificação não disponível",
            released = movieResponse.released ?: "Data de lançamento não disponível",
            runtime = movieResponse.runtime ?: "Duração não disponível",
            writer = movieResponse.writer ?: "Escritor não disponível",
            actors = movieResponse.actors ?: "Atores não disponíveis",
            plot = movieResponse.plot ?: "Sinopse não disponível",
            country = movieResponse.country ?: "País não disponível",
            awards = movieResponse.awards ?: "Prêmios não disponíveis",
            metascore = movieResponse.metascore ?: "Metascore não disponível",
            imdbRating = movieResponse.imdbRating ?: "Classificação IMDb não disponível",
            imdbVotes = movieResponse.imdbVotes ?: "Votos IMDb não disponíveis",
            type = movieResponse.type ?: "Tipo não disponível",
            dvd = movieResponse.dvd ?: "DVD não disponível",
            boxOffice = movieResponse.boxOffice ?: "Bilheteira não disponível",
            website = movieResponse.website ?: "Website não disponível",
            production = movieResponse.production ?: "Produção não disponível",
            response = movieResponse.response ?: "Resposta não disponível",
        )
    }
}
