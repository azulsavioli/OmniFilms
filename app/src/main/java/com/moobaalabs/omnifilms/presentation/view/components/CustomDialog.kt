package com.moobaalabs.omnifilms.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage

@Composable
fun CustomDialog(
    posterUrl: String,
    title: String,
    year: String,
    director: String,
    language: String,
    actors: String,
    genre: String,
    country: String,
    awards: String,
    production: String,
    plot: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.8f))
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.width(270.dp)
                    ) {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 18.sp,
                            maxLines = 3,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .clickable {
                                onDismiss()
                            }
                    )
                }
                AsyncImage(
                    model = if (posterUrl.isEmpty() || posterUrl == "N/A") {
                        "https://wallpapers.com/images/high/static-background-ya21qqq0vvkjh3it.webp"
                    } else {
                        posterUrl
                    },
                    contentDescription = "Poster em tela cheia",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
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
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                                append("Língua: ")
                            }
                            append(language)
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
                                append("Atores: ")
                            }
                            append(actors)
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
                                append("Gênero: ")
                            }
                            append(genre)
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
                                append("País: ")
                            }
                            append(country)
                        },
                        textAlign = TextAlign.Left,
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                                append("Premios: ")
                            }
                            append(awards)
                        },
                        textAlign = TextAlign.Left,
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                                append("Produção: ")
                            }
                            append(production)
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
                                append("Sinopse: ")
                            }
                            append(plot)
                        },
                        textAlign = TextAlign.Left,
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        maxLines = Int.MAX_VALUE
                    )
                }
            }
        }
    }
}


