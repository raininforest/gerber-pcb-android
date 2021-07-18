package com.github.raininforest.gerberpcb.model

import io.reactivex.rxjava3.core.Observable

/**
 * Created by Sergey Velesko on 18.07.2021
 */
interface IDataService {
    fun list(): Observable<List<GerberItemUi>>
    fun addItem(filename: String)
    fun removeItem(id: String)
    fun changeItemVisibility(id: String, visibility: Boolean)
    fun clear()

    //TODO add graphics(): Observable<GraphicStream> ?
}