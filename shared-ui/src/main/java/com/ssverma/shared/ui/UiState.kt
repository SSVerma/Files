package com.ssverma.shared.ui

import androidx.compose.runtime.Stable

@Stable
sealed interface UiState<out S, out F> {
    object Idle : UiState<Nothing, Nothing>
    data class Success<out S>(val data: S) : UiState<S, Nothing>
    object Loading : UiState<Nothing, Nothing>
    data class Error<out F>(
        val failure: F
    ) : UiState<Nothing, F>
}