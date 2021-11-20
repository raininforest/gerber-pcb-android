package com.github.raininforest.gerberpcb.ui.layers

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.raininforest.gerberpcb.model.GerberRepository
import com.github.raininforest.logger.Logger
import kotlinx.coroutines.*

/**
 * [ViewModel] for [LayersFragment]
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class LayersViewModel(
    private val gerberRepository: GerberRepository
) : ViewModel() {

    private val _data: MutableLiveData<LayersScreenState> = MutableLiveData()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val vmCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    val data: LiveData<LayersScreenState>
        get() = _data
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun list() {
        cancelJob()
        vmCoroutineScope.launch {
            _data.postValue(LayersScreenState.Success(gerberRepository.list()))
        }
    }

    fun gerberAdded(fileUri: Uri) {
        _isLoading.value = true
        vmCoroutineScope.launch {
            val processed = gerberRepository.addItem(fileUri)
            if (processed) {
                _data.postValue(LayersScreenState.Success(gerberRepository.list()))
                _isLoading.postValue(false)
            } else {
                _data.postValue(LayersScreenState.Error("Can't process gerber!"))
                _isLoading.postValue(false)
            }
        }
    }

    fun gerberRemoved(id: String) {
        vmCoroutineScope.launch {
            gerberRepository.removeItem(id)
            _data.postValue(LayersScreenState.Success(gerberRepository.list()))
        }
    }

    fun gerberVisibilityChanged(id: String, visibility: Boolean) {
        vmCoroutineScope.launch {
            gerberRepository.changeItemVisibility(id, visibility)
            _data.postValue(LayersScreenState.Success(gerberRepository.list()))
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun handleError(e: Throwable) {
        Logger.d(e.localizedMessage ?: "Error getting data from GerberRepository")
        _isLoading.value = false
        _data.value = LayersScreenState.Error("Data loading failed!")
    }

    private fun cancelJob() {
        vmCoroutineScope.coroutineContext.cancelChildren()
    }
}