package com.github.raininforest.gerberpcb.model

interface DataService {
    fun getList(): GerberList
    fun addItem(gerberUri: String)
    fun removeItem(gerberName: String)
    fun setItemVisibility(visible: Boolean)
    fun clear()
    fun removeListeners()
}