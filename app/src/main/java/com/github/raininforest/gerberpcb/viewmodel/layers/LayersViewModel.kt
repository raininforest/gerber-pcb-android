package com.github.raininforest.gerberpcb.viewmodel.layers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.raininforest.gerberpcb.model.IDataService
import com.github.raininforest.gerberpcb.model.DataService

class LayersViewModel : ViewModel() {
    private val liveData: MutableLiveData<LayersScreenState> = MutableLiveData()
    private val IDataService: IDataService = DataService()

    fun getLiveData(): LiveData<LayersScreenState> {
        return liveData
    }

    fun getList() {
        liveData.value = LayersScreenState.Success(IDataService.getList())
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
        IDataService.removeListeners()
    }
}