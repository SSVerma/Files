package com.ssverma.files.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ssverma.core.navigation.StandaloneDestination

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FilesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: StandaloneDestination = FilesTopLevelDestination
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination.placeholderRoute.asNavRoute(),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Up
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Down
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down
            )
        },
        modifier = modifier
    ) {
        topLevelNavGraph(navController)
    }
}