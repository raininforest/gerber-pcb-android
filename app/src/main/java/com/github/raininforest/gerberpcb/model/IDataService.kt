package com.github.raininforest.gerberpcb.model

interface IDataService {
    fun getList(): GerberList
    fun addItem(gerberUri: String)
    fun removeItem(gerberName: String)
    fun setItemVisibility(visible: Boolean)
    fun clear()
    fun removeListeners()
}