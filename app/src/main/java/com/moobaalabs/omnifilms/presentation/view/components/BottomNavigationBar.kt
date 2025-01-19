package com.moobaalabs.omnifilms.presentation.view.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.moobaalabs.omnifilms.presentation.theme.TerciaryBlue

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val navBarItems = remember { NavBarItems.values() }
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    var selectedIndex by remember { mutableIntStateOf(0) }

    when (currentRoute) {
        "search_page" -> selectedIndex = 0
        "favorites_page" -> selectedIndex = 1
    }

    AnimatedNavigationBar(
        modifier = Modifier
            .background(Color(0xFF1C1C1E))
            .padding(16.dp)
            .height(55.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(cornerRadius = 16.dp),
        ballAnimation = Parabolic(tween(durationMillis = 300)),
        indentAnimation = Height(tween(durationMillis = 300)),
        barColor = TerciaryBlue,
        ballColor = TerciaryBlue
    ) {
        navBarItems.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        when (item) {
                            NavBarItems.Home -> navController.navigate("search_page")
                            NavBarItems.Favorites -> navController.navigate("favorites_page")
                        }
                        selectedIndex = item.ordinal
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = "Bottom nav bar item",
                    tint = if (selectedIndex == item.ordinal) Color.White else Color.White.copy(
                        alpha = 0.5f
                    ),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

enum class NavBarItems(val icon: ImageVector) {
    Home(Icons.Filled.Home),
    Favorites(Icons.Filled.Favorite)
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}