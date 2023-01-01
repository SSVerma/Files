package com.ssverma

object Modules {
    object Core {
        const val ui = ":core-ui"
        const val navigation = ":core-navigation"
        const val di = ":core-di"
        const val image = ":core-image"
        const val storage = ":core-storage"
    }

    object Shared {
        const val ui = ":shared-ui"
        const val domain = ":shared-domain"
        const val data = ":shared-data"
    }

    object Feature {
        const val browse = ":feature-browse"
        const val browseNavigation = ":feature-browse-navigation"

        const val clean = ":feature-clean"
        const val cleanNavigation = ":feature-clean-navigation"

        const val nearbyShare = ":feature-nearby-share"
        const val nearbyShareNavigation = ":feature-nearby-share-navigation"
    }
}