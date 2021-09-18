package com.github.raininforest.gerberpcb.model

import io.reactivex.rxjava3.core.Observable

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class DataService : IDataService {

    //TODO
    override fun list(): Observable<List<GerberItemUi>> =
        Observable.just(
            listOf(
                GerberItemUi(),
                GerberItemUi(),
                GerberItemUi()
            )
        )

    override fun addItem(filename: String) {
        TODO("Not yet implemented")
    }

    override fun removeItem(id: String) {
        TODO("Not yet implemented")
    }

    override fun changeItemVisibility(id: String, visibility: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}