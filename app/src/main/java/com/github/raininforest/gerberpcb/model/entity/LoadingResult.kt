package com.github.raininforest.gerberpcb.model.entity

sealed class LoadingResult {
    object Success : LoadingResult()
    class Error(val errorMessage: String) : LoadingResult()
}
