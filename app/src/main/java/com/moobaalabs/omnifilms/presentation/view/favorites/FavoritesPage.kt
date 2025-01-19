package com.moobaalabs.omnifilms.presentation.view.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.presentation.view.components.MovieCard
import com.moobaalabs.omnifilms.presentation.view.components.SectionHeader
import com.moobaalabs.omnifilms.presentation.viewmodel.MovieViewModel

@Composable
fun FavoritesPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val favoriteMovies by viewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteMovies()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(16.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(26.dp))

            SectionHeader(title = "Filmes Favoritos")

            Spacer(modifier = Modifier.height(26.dp))
        }

        items(favoriteMovies.size) { index ->
            val reversedIndex = favoriteMovies.size - 1 - index
            val movie: MovieEntity = favoriteMovies[reversedIndex]

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                MovieCard(
                    title = movie.title,
                    year = movie.year,
                    director = movie.director,
                    posterUrl = movie.poster,
                    isFavorite = movie.isFavorite,
                    language = movie.language,
                    onRemoveClick = null,
                    onFavoriteClick = {
                        viewModel.toggleFavorite(movie)
                    },
                    genre = movie.genre,
                    actors = movie.actors,
                    country = movie.country,
                    awards = movie.awards,
                    production = movie.production,
                    sinopse = movie.plot
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}