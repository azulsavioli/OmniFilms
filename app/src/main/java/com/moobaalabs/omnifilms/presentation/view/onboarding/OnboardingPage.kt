package com.moobaalabs.omnifilms.presentation.view.onboarding

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moobaalabs.omnifilms.MainActivity
import com.moobaalabs.omnifilms.R
import com.moobaalabs.omnifilms.presentation.theme.PrimaryBlue
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage(
    navController: NavController,
    modifier: Modifier
) {

    val images = listOf(
        R.drawable.popcorn_onboard_1,
        R.drawable.claquete_onboard_2,
        R.drawable.camera_onboard_3,
    )

    val titles = listOf(
        "Descubra filmes incríveis",
        "Favoritos em um só lugar",
        "Saiba mais sobre filmes",
    )

    val descriptions = listOf(
        "Digite o título ou parte dele e encontre detalhes rapidamente.",
        "Pesquise e favorite seus filmes para acessar sempre que quiser.",
        "Confira mais detalhes dos filmes, como elenco, direção e muito mais.",
    )

    val pagerState = rememberPagerState(
        pageCount = { images.size },
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) { currentPage ->
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .background(PrimaryBlue)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = images[currentPage]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = descriptions[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    letterSpacing = 0.7.sp,
                    color = Color.White, fontWeight = FontWeight.Normal
                )
            }
        }
        ButtonSection(pagerState, navController, images.size)
    }
}

@Composable
fun ButtonSection(
    pagerState: PagerState,
    navController: NavController,
    count: Int,
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        if (pagerState.currentPage != 2) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Voltar",
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                val prevPage = pagerState.currentPage - 1
                                pagerState.animateScrollToPage(prevPage)
                            }
                        },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                PagerIndicator(
                    count = count,
                    current = pagerState.currentPage,
                    modifier = Modifier.padding(
                        bottom = 3.dp,
                        top = 5.dp
                    )
                )
                Text(
                    text = "Próximo",
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                val nextPage = pagerState.currentPage + 1
                                pagerState.animateScrollToPage(nextPage)
                            }
                        },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }
        } else {
            OutlinedButton(
                colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                ),
                onClick = {
                    onBoardingIsFinished(context = navController.context as MainActivity)
                    navController.navigate("search_page")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Começar",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            }
        }
    }
}

@Composable
fun PagerIndicator(
    count: Int,
    current: Int,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(count) {
            IndicatorSingleDot(
                isSelected = it == current
            )
        }
    }
}

@Composable
fun IndicatorSingleDot(
    isSelected: Boolean
) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 30.dp else 10.dp
    )
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
                shape = CircleShape
            )
    )
}


private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()
}
