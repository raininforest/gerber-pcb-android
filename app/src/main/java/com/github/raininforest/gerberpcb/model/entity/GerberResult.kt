package com.github.raininforest.gerberpcb.model.entity

sealed class GerberResult {
    class Success(val gerber: Gerber) : GerberResult()
    class Error(val errorMessage: String) : GerberResult()
}
