package com.github.raininforest.gerberpcb.viewmodel.layers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.raininforest.gerberpcb.model.IDataService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class LayersViewModel (
    private val dataService: IDataService
) : ViewModel() {

    private val data: MutableLiveData<LayersScreenState> = MutableLiveData()
    private val progressValue: MutableLiveData<String> = MutableLiveData()

    fun data(): LiveData<LayersScreenState> {
        dataService.list()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { data.value = LayersScreenState.Success(it) },
            { it.printStackTrace() }
        )
        return data
    }

    fun gerberAdded(filename: String) {
        dataService.addItem(filename)
        //TODO subscribe on progress and change livedata (progressValue) value to loading
    }

    fun gerberRemoved(id: String) {
        dataService.removeItem(id)
        //TODO subscribe on completable, write log on error
    }

    fun gerberVisibilityChanged(id: String, visibility: Boolean) {
        dataService.changeItemVisibility(id, visibility)
        //TODO subscribe on Completable?
    }
}