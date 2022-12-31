package com.ssverma.shared.domain.failure

data class Failure<out T>(
    val failureType: T
)