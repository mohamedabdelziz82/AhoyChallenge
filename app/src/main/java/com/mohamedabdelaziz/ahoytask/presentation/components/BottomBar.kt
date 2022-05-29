package com.mohamedabdelaziz.ahoytask.presentation.components

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohamedabdelaziz.ahoytask.core.ui.theme.Primary
import com.mohamedabdelaziz.ahoytask.presentation.screens.FavoriteScreen
import com.mohamedabdelaziz.ahoytask.presentation.screens.ForecastScreen
import com.mohamedabdelaziz.ahoytask.presentation.screens.HomeScreen
import com.mohamedabdelaziz.ahoytask.presentation.screens.SettingsScreen

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Forecast,
        NavigationItem.Favourite,
        NavigationItem.Settings,
    )
    BottomNavigation(
        backgroundColor = Primary,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title)
                    )
                },
                label = { Text(text = stringResource(id = item.title)) },
                selectedContentColor = Color.White.copy(0.4f),
                unselectedContentColor = Color.White,
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Forecast.route) {
            ForecastScreen()
        }
        composable(NavigationItem.Favourite.route) {
            FavoriteScreen()
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
}