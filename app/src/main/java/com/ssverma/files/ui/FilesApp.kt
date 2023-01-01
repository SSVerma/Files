package com.ssverma.files.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ssverma.core.navigation.Destination
import com.ssverma.core.ui.asString
import com.ssverma.core.ui.icon.asIcon
import com.ssverma.core.ui.theme.FilesTheme
import com.ssverma.files.navigation.FilesNavHost
import com.ssverma.files.navigation.NavigationType
import com.ssverma.files.navigation.TopLevelNavItem
import com.ssverma.files.navigation.TopLevelNavItems

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilesApp(
    windowSizeClass: WindowSizeClass,
    appState: FilesAppState = rememberFilesAppState(windowSizeClass = windowSizeClass)
) {

    val systemBarColors = when (appState.windowInfo.navigationType) {
        NavigationType.NavigationBar -> {
            MaterialTheme.colorScheme.surfaceColorAtElevation(3.0.dp)
        }
        NavigationType.NavigationRail,
        NavigationType.NavigationDrawer -> Color.Transparent
    }

    FilesTheme(
        statusBarColor = systemBarColors,
        navigationBarColor = systemBarColors
    ) {
        Scaffold(
            bottomBar = {
                if (appState.windowInfo.navigationType == NavigationType.NavigationBar) {
                    FilesNavigationBar(
                        currentTopLevelDestination = appState.currentTopLevelDestination,
                        onTopLevelNavItemSelected = { topLevelNavItem ->
                            appState.selectTopLevelNavItem(topLevelNavItem)
                        }
                    )
                }
            }
        ) { innerPadding ->
            when (appState.windowInfo.navigationType) {
                NavigationType.NavigationBar -> {
                    FilesNavHost(navController = appState.navController)
                }

                NavigationType.NavigationRail -> {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .consumedWindowInsets(innerPadding)
                            .windowInsetsPadding(
                                WindowInsets.safeDrawing.only(
                                    WindowInsetsSides.Horizontal
                                )
                            )
                    ) {
                        FilesNavigationRail(
                            currentTopLevelDestination = appState.currentTopLevelDestination,
                            onTopLevelNavItemSelected = { topLevelNavItem ->
                                appState.selectTopLevelNavItem(topLevelNavItem)
                            }
                        )

                        FilesNavHost(navController = appState.navController)
                    }
                }

                NavigationType.NavigationDrawer -> {
                    FilesNavigationDrawer(
                        currentTopLevelDestination = appState.currentTopLevelDestination,
                        onTopLevelNavItemSelected = { topLevelNavItem ->
                            appState.selectTopLevelNavItem(topLevelNavItem)
                        }
                    ) {
                        FilesNavHost(navController = appState.navController)
                    }
                }
            }
        }
    }
}

@Composable
fun FilesNavigationBar(
    currentTopLevelDestination: Destination?,
    onTopLevelNavItemSelected: (TopLevelNavItem) -> Unit,
    modifier: Modifier = Modifier,
    navItems: List<TopLevelNavItem> = TopLevelNavItems,
) {
    NavigationBar(modifier) {
        navItems.forEach { navItem ->
            val selected = currentTopLevelDestination == navItem.destination

            NavigationBarItem(
                icon = { navItem.iconRes.asIcon() },
                label = { Text(text = navItem.title.asString()) },
                selected = selected,
                onClick = {
                    onTopLevelNavItemSelected(navItem)
                }
            )
        }
    }
}

@Composable
private fun FilesNavigationRail(
    currentTopLevelDestination: Destination?,
    onTopLevelNavItemSelected: (TopLevelNavItem) -> Unit,
    modifier: Modifier = Modifier,
    navItems: List<TopLevelNavItem> = TopLevelNavItems,
) {
    NavigationRail(modifier) {
        navItems.forEach { navItem ->
            val selected = currentTopLevelDestination == navItem.destination

            NavigationRailItem(
                icon = { navItem.iconRes.asIcon() },
                label = { Text(text = navItem.title.asString()) },
                selected = selected,
                onClick = {
                    onTopLevelNavItemSelected(navItem)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilesNavigationDrawer(
    currentTopLevelDestination: Destination?,
    onTopLevelNavItemSelected: (TopLevelNavItem) -> Unit,
    modifier: Modifier = Modifier,
    navItems: List<TopLevelNavItem> = TopLevelNavItems,
    content: @Composable () -> Unit
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(modifier) {
                navItems.forEach { navItem ->
                    val selected = currentTopLevelDestination == navItem.destination
                    NavigationDrawerItem(
                        icon = { navItem.iconRes.asIcon() },
                        label = { Text(text = navItem.title.asString()) },
                        selected = selected,
                        onClick = {
                            onTopLevelNavItemSelected(navItem)
                        }
                    )
                }
            }
        },
        content = content
    )
}