package com.mohamedabdelaziz.ahoytask.presentation.components

import com.mohamedabdelaziz.ahoytask.R

sealed class NavigationItem(var route: String, var icon: Int, var title: Int) {
    object Home : NavigationItem("home", R.drawable.ic_home,  R.string.lbl_home)
    object Forecast : NavigationItem("forecast", R.drawable.ic_forecast, R.string.lbl_forecast)
    object Favourite : NavigationItem("favourite", R.drawable.ic_favorite_tab, R.string.lbl_favourite)
    object Settings : NavigationItem("settings", R.drawable.ic_settings, R.string.lbl_settings)
 }