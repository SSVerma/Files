package com.ssverma.files.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ssverma.core.navigation.Destination
import com.ssverma.core.navigation.GraphDestination
import com.ssverma.core.navigation.navigation
import com.ssverma.core.ui.UiText
import com.ssverma.core.ui.icon.IconResource
import com.ssverma.feature.browse.navigation.BrowseDestination
import com.ssverma.feature.browse.navigation.browseGraph
import com.ssverma.feature.clean.navigation.CleanDestination
import com.ssverma.feature.clean.navigation.cleanGraph
import com.ssverma.feature.nearby.share.navigation.NearbyShareDestination
import com.ssverma.feature.nearby.share.navigation.nearbyShareGraph

sealed class TopLevelNavItem(
    val destination: Destination,
    val title: UiText,
    val iconRes: IconResource
) {
    object Browse : TopLevelNavItem(
        destination = BrowseDestination,
        title = UiText.StaticText(com.ssverma.feature.browse.R.string.browse),
        iconRes = IconResource.DrawableResource(resId = com.ssverma.feature.browse.R.drawable.ic_browse)
    )

    object Clean : TopLevelNavItem(
        destination = CleanDestination,
        title = UiText.StaticText(com.ssverma.feature.clean.R.string.clean),
        iconRes = IconResource.DrawableResource(resId = com.ssverma.feature.clean.R.drawable.ic_clean)
    )

    object NearbyShare : TopLevelNavItem(
        destination = NearbyShareDestination,
        title = UiText.StaticText(com.ssverma.feature.nearby.share.R.string.nearby_share),
        iconRes = IconResource.DrawableResource(resId = com.ssverma.feature.nearby.share.R.drawable.ic_nearby_share)
    )
}

val TopLevelNavItems = listOf(
    TopLevelNavItem.Browse,
    TopLevelNavItem.Clean,
    TopLevelNavItem.NearbyShare
)

object FilesTopLevelDestination : GraphDestination("home")

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.topLevelNavGraph(
    navController: NavController
) = navigation(
    graphDestination = FilesTopLevelDestination,
    startDestination = BrowseDestination
) {
    browseGraph(navController)
    cleanGraph(navController)
    nearbyShareGraph(navController)
}