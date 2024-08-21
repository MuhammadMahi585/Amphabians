package com.example.amphabians.Screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.amphabians.data.AmphibiansPhotoRepository
import com.example.amphabians.model.AmphibiansPhotos
import com.example.amphabians.ui.theme.AmphibiansPhotoApplication
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibiansUIState {
    data class Success(val photos: List<AmphibiansPhotos>) : AmphibiansUIState
    object Error : AmphibiansUIState
    object Loading : AmphibiansUIState
}

class AmphibiansViewModel(private val amphibiansPhotoRepository: AmphibiansPhotoRepository): ViewModel() {
      var amphibiansUIState:AmphibiansUIState by mutableStateOf(AmphibiansUIState.Loading)
          private set
    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUIState = AmphibiansUIState.Loading
            amphibiansUIState = try {
                AmphibiansUIState.Success(amphibiansPhotoRepository.getList())
            } catch (e: IOException) {
                AmphibiansUIState.Error
            } catch (e: HttpException) {
               AmphibiansUIState.Error
            }
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansPhotoApplication)
                val amphibiansRepository = application.container.amphibiansPhotoRepository
                AmphibiansViewModel(amphibiansPhotoRepository = amphibiansRepository)
            }
        }
    }
}