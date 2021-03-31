package com.github.raininforest.gerberpcb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LayersViewModel : ViewModel() {
    private val liveData: MutableLiveData<LayersScreenState> = MutableLiveData()

    fun getLiveData(): LiveData<LayersScreenState> {
        return liveData
        //TODO repository.getGerberList()
    }

    fun addFile(gerberUri: String) {
        liveData.value = LayersScreenState.Loading
        //TODO repository.add(gerberUri). должен вернуть новый увеличенный список в лайвдату
    }

    fun setItemVisible(gerberName: String, visible: Boolean) {
        //TODO repository.setVisible(gerberName)
    }

    fun setItemColor(gerberName: String, color: String) {
        //TODO setItemColor(gerberName: String, color: String)
    }

    fun removeItem(gerberName: String) {
        //TODO repository.remove(gerberName). должен вернуть новый уменьшенный список в лайвдату
    }

    fun clear() {
        //TODO repository.clear(). должен вернуть пустоту
    }
}