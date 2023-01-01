package com.ssverma.feature.browse.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ssverma.core.navigation.composable
import com.ssverma.feature.browse.ui.BrowseScreen

fun NavGraphBuilder.browseGraph(
    navController: NavController
) = composable(destination = BrowseDestination) {
    BrowseScreen()
}