package com.ssverma.files.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ssverma.core.navigation.Destination
import com.ssverma.files.navigation.*
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberFilesAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberAnimatedNavController()
): FilesAppState {
    return remember(navController, coroutineScope, windowSizeClass) {
        FilesAppState(navController, coroutineScope, windowSizeClass)
    }
}

@Stable
class FilesAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: Destination?
        @Composable get() = TopLevelNavItems.find {
            it.destination.placeholderRoute.asNavRoute() == currentDestination?.route
        }?.destination

    val isCurrentDestinationTopLevel: Boolean
        @Composable get() = currentTopLevelDestination != null

    val windowInfo: AppWindowInfo
        get() = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                AppWindowInfo(
                    navigationType = NavigationType.NavigationBar,
                    paneType = ContentPaneType.SinglePane
                )
            }
            WindowWidthSizeClass.Medium -> {
                AppWindowInfo(
                    navigationType = NavigationType.NavigationRail,
                    paneType = ContentPaneType.SinglePane
                )
            }
            WindowWidthSizeClass.Expanded -> {
                AppWindowInfo(
                    navigationType = NavigationType.NavigationDrawer,
                    paneType = ContentPaneType.DualPane
                )
            }
            else -> {
                AppWindowInfo(
                    navigationType = NavigationType.NavigationBar,
                    paneType = ContentPaneType.SinglePane
                )
            }
        }

    fun selectTopLevelNavItem(navItem: TopLevelNavItem) {
        navController.navigate(navItem.destination.placeholderRoute.asNavRoute()) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}