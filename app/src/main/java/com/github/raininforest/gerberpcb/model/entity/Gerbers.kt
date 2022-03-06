package com.github.raininforest.gerberpcb.model.entity

data class Gerbers(val list: List<Gerber>) {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }
}
