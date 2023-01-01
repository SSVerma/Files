package com.ssverma.files.navigation

enum class NavigationType {
    NavigationBar,
    NavigationRail,
    NavigationDrawer
}

enum class ContentPaneType {
    SinglePane, DualPane
}

class AppWindowInfo(
    val navigationType: NavigationType,
    val paneType: ContentPaneType
)