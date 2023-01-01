package com.ssverma.feature.nearby.share.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ssverma.core.navigation.composable
import com.ssverma.feature.nearby.share.ui.NearbyShareScreen

fun NavGraphBuilder.nearbyShareGraph(
    navController: NavController
) = composable(destination = NearbyShareDestination) {
    NearbyShareScreen()
}