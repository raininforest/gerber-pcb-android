package com.github.raininforest.gerberpcb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.raininforest.gerberpcb.model.DataService
import com.github.raininforest.gerberpcb.model.DataServiceImpl

class LayersViewModel : ViewModel() {
    private val liveData: MutableLiveData<LayersScreenState> = MutableLiveData()
    private val dataService: DataService = DataServiceImpl

    fun getLiveData(): LiveData<LayersScreenState> {
        return liveData
    }

    fun getList() {
        liveData.value = LayersScreenState.Success(dataService.getList())
    }

    fun addItem(gerberUri: String) {
        liveData.value = LayersScreenState.Loading
        //TODO repository.add(gerberUri). должен вернуть новый увеличенный список в лайвдату
    }

    fun setItemVisibility(gerberName: String, visible: Boolean) {
        //TODO repository.setVisible(gerberName)
    }

    fun removeItem(gerberName: String) {
        //TODO repository.remove(gerberName). должен вернуть новый уменьшенный список в лайвдату
    }

    fun clearList() {
        //TODO repository.clear(). должен вернуть пустоту
    }

    override fun onCleared() {
        super.onCleared()
        dataService.removeListeners()
    }
}