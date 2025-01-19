package com.moobaalabs.omnifilms

import android.content.Context
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moobaalabs.omnifilms.presentation.theme.OmniFilmsTheme
import com.moobaalabs.omnifilms.presentation.theme.PrimaryBlue
import com.moobaalabs.omnifilms.presentation.view.components.BottomNavigationBar
import com.moobaalabs.omnifilms.presentation.view.favorites.FavoritesPage
import com.moobaalabs.omnifilms.presentation.view.onboarding.OnboardingPage
import com.moobaalabs.omnifilms.presentation.view.search.SearchPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            OmniFilmsTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {},
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        if (currentRoute != null && currentRoute != "onboarding") {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = if (getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
                                .getBoolean("isFinished", false)
                        ) "search_page" else "onboarding",
                        modifier = Modifier
                            .padding(paddingValues)
                            .background(PrimaryBlue)
                    ) {
                        composable("search_page") {
                            SearchPage(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable("favorites_page") {
                            FavoritesPage(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable("onboarding") {
                            OnboardingPage(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable("home") { /* Your Home Page */ }
                    }
                }
            }
        }
    }
}
