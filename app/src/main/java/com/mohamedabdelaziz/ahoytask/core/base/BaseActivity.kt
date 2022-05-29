package com.mohamedabdelaziz.ahoytask.core.base

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresPermission
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.mohamedabdelaziz.ahoytask.core.ui.theme.AhoyWeatherTaskTheme
import com.mohamedabdelaziz.ahoytask.core.utils.LocationPermissionDetails
import com.mohamedabdelaziz.ahoytask.core.utils.LocationPermissionNotAvailable
import com.mohamedabdelaziz.ahoytask.presentation.components.BottomBar
import com.mohamedabdelaziz.ahoytask.presentation.components.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AhoyWeatherTaskTheme {
                val permissionState =
                    rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

                when (val status = permissionState.status) {
                    PermissionStatus.Granted -> Greeting()
                    is PermissionStatus.Denied -> {
                        if (status.shouldShowRationale) {
                            LocationPermissionNotAvailable(onContinueClick = permissionState::launchPermissionRequest)
                        } else {
                            LocationPermissionDetails(onContinueClick = permissionState::launchPermissionRequest)
                        }
                    }

                }
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @Composable
    fun Greeting() {
         val navController = rememberNavController()
        // A surface container using the 'background' color from the theme
        Scaffold(
            bottomBar = { BottomBar(navController = navController) }
        ) {
            Navigation(navController)
//
        }
    }



    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AhoyWeatherTaskTheme {
            Greeting()
        }
    }


}