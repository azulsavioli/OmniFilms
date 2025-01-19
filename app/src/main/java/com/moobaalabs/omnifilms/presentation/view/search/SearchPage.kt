package com.moobaalabs.omnifilms.presentation.view.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.presentation.theme.SecondaryGreen
import com.moobaalabs.omnifilms.presentation.view.components.MovieCard
import com.moobaalabs.omnifilms.presentation.view.components.SearchTopBar
import com.moobaalabs.omnifilms.presentation.view.components.SectionHeader
import com.moobaalabs.omnifilms.presentation.viewmodel.MovieViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchPage(
    modifier: Modifier,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    val recentlyViewed by viewModel.recentlyViewed.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.getRecentlyViewedMovies()
        viewModel.isLoading
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(16.dp)
    ) {
        stickyHeader {
            SearchTopBar(
                query = query,
                onSearch = { newQuery ->
                    query = newQuery
                },
                onSearchConfirmed = {
                    if (query.isNotEmpty()) {
                        viewModel.searchMovie(query, context).also {
                            query = ""
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Digite um título para pesquisar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(26.dp))
            SectionHeader(title = "Últimas pesquisas")
            Spacer(modifier = Modifier.height(26.dp))
        }

        item {
            if (viewModel.isLoading.collectAsState().value) {
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .background(Color(0xFF1C1C1E))
                        .height(360.dp)
                        .width(380.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF1C1C1E)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 8.dp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(80.dp)
                                .height(80.dp),
                            color = SecondaryGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        items(recentlyViewed.size) { index ->
            val reversedIndex = recentlyViewed.size - 1 - index
            val movie: MovieEntity = recentlyViewed[reversedIndex]
            if (!viewModel.isLoading.collectAsState().value) {
                MovieCard(
                    title = movie.title,
                    year = movie.year,
                    director = movie.director,
                    posterUrl = movie.poster,
                    genre = movie.genre,
                    isFavorite = movie.isFavorite,
                    language = movie.language,
                    actors = movie.actors,
                    country = movie.country,
                    awards = movie.awards,
                    production = movie.production,
                    sinopse = movie.plot,
                    onRemoveClick = {
                        viewModel.removeRecentlyViewedMovie(movie)
                    },
                    onFavoriteClick = {
                        viewModel.toggleFavorite(movie)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
