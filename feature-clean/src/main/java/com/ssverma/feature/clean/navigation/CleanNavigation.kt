package com.ssverma.feature.clean.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ssverma.core.navigation.composable
import com.ssverma.feature.clean.ui.CleanScreen

fun NavGraphBuilder.cleanGraph(
    navController: NavController
) = composable(destination = CleanDestination) {
    CleanScreen()
}