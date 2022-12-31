package com.ssverma.shared.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun <S, F> DriveCompose(
    uiState: UiState<S, F>,
    loading: @Composable () -> Unit = { DefaultLoadingIndicator() },
    errorContent: @Composable (failure: F) -> Unit = { DefaultCoreErrorIndicator() },
    idleContent: @Composable () -> Unit = {},
    content: @Composable (data: S) -> Unit,
) {
    when (uiState) {
        is UiState.Idle -> {
            idleContent()
        }
        is UiState.Error -> {
            errorContent(uiState.failure)
        }
        UiState.Loading -> {
            loading()
        }
        is UiState.Success -> {
            content(uiState.data)
        }
    }
}

@Composable
fun DefaultLoadingIndicator(modifier: Modifier = Modifier) {
    return ScreenLoadingIndicator(modifier)
}

@Composable
fun DefaultCoreErrorIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // TODO: Place an error indicator illustration
        Text(text = stringResource(id = R.string.something_went_wrong))
    }
}