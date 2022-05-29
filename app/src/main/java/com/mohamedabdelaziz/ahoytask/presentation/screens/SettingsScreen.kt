package com.mohamedabdelaziz.ahoytask.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.ui.theme.LightNeutral1
import com.mohamedabdelaziz.ahoytask.presentation.components.SettingContent
import com.mohamedabdelaziz.ahoytask.presentation.components.WeatherTopAppBar

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightNeutral1)
    ) {
        WeatherTopAppBar(stringResource(id = R.string.lbl_settings))
        SettingContent()
    }
    }