package com.github.raininforest.gerberpcb.ui.screens.layersscreen

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.raininforest.gerberpcb.model.GerberRepository
import com.github.raininforest.gerberpcb.model.entity.GerberResult
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
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    val data: LiveData<LayersScreenState>
        get() = _data
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun init() {
        cancelJob()
        _data.value = gerberRepository.gerbers.toScreenState()
    }

    fun gerberAdded(fileUri: Uri, fileName: String) {
        _isLoading.value = true
        vmCoroutineScope.launch {
            val result = gerberRepository.addItem(fileUri, fileName)
            withContext(Dispatchers.Main) {
                when (result) {
                    is GerberResult.Success -> {
                        _data.value = gerberRepository.gerbers.toScreenState()
                        _isLoading.value = false
                    }
                    is GerberResult.Error -> {
                        _data.value =
                            LayersScreenState.Error("Can't process gerber!", result.errorMessage)
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun gerberRemoved(id: String) {
        gerberRepository.removeItem(id)
        _data.value = gerberRepository.gerbers.toScreenState()
    }

    fun gerberVisibilityChanged(id: String, visibility: Boolean) {
        gerberRepository.changeItemVisibility(id, visibility)
        _data.value = gerberRepository.gerbers.toScreenState()
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