package com.github.raininforest.gerberpcb.model

sealed class GerberResult {
    class Success(val gerber: Gerber) : GerberResult()
    class Error(val errorMessage: String) : GerberResult()
}
