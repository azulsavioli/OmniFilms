package com.moobaalabs.omnifilms.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.moobaalabs.omnifilms.presentation.theme.SecondaryRed

@Composable
fun MovieCard(
    posterUrl: String,
    title: String,
    year: String,
    director: String,
    language: String,
    actors: String?,
    country: String?,
    awards: String?,
    production: String?,
    genre: String,
    sinopse: String?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit = {},
    onRemoveClick: (() -> Unit)? = null,
) {
    var isPosterOpen by remember { mutableStateOf(false) }
    var isFavoriteMovie by remember { mutableStateOf(isFavorite) }
    val currentFavoriteState by rememberUpdatedState(isFavoriteMovie)

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(350.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF2C2C2E))
            .clickable {
                isPosterOpen = true
            }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = title,
                        letterSpacing = 1.sp,
                        textAlign = TextAlign.Left,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 3
                    )
                }
                if (onRemoveClick != null) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .clickable {
                                onRemoveClick()
                            }
                    )
                }
            }
            AsyncImage(
                model = if (posterUrl.isEmpty() || posterUrl == "N/A") {
                    "https://wallpapers.com/images/high/static-background-ya21qqq0vvkjh3it.webp"
                } else {
                    posterUrl
                },
                contentDescription = "Poster do filme",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Column {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                                    append("Ano: ")
                                }
                                append(year)
                            },
                            textAlign = TextAlign.Left,
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            maxLines = 2
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                                    append("Diretor: ")
                                }
                                append(director)
                            },
                            textAlign = TextAlign.Left,
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            maxLines = 2
                        )
                    }
                    IconButton(
                        onClick = {
                            onFavoriteClick()
                            isFavoriteMovie = !isFavoriteMovie
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (currentFavoriteState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (currentFavoriteState) "Desfavoritar" else "Favoritar",
                            tint = SecondaryRed,
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(12.dp)
                ) {
                    genre.split(",").forEach { genre ->
                        CategoryChip(
                            text = genre,
                            isSelected = true,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                }
            }
        }
    }

    if (isPosterOpen) {
        CustomDialog(
            posterUrl = posterUrl,
            title = title,
            year = year,
            director = director,
            language = language,
            actors = actors!!,
            country = country!!,
            awards = awards!!,
            production = production!!,
            genre = genre,
            plot = sinopse!!,
            onDismiss = { isPosterOpen = false }
        )
    }
}