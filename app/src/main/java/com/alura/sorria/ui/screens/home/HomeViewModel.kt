package com.alura.sorria.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alura.sorria.domain.PostUseCase
import com.alura.sorria.ui.components.screenItems
import com.alura.sorria.speechToText.SpeechToText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val speechToText: SpeechToText,
    private var postUseCase: PostUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            posts = emptyList(),
            navigateTo = screenItems.first().first
        )
    )
    var uiState = _uiState.asStateFlow()

    init {
        loadPosts()
        viewModelScope.launch {
            speechToText.state.collect { speechState ->
                _uiState.update {
                    it.copy(
                        listenedText = speechState.text,
                        isListening = speechState.isListening,
                        errorOnListening = speechState.error
                    )
                }

                if (speechState.text.isNotEmpty()) {
                    viewModelScope.launch {
                        delay(2000)
                        _uiState.update {
                            it.copy(
                                isListening = false,
                                listenedText = ""
                            )
                        }
                    }
                }

                val navigationKeywords = screenItems.map { it.first.lowercase(Locale.getDefault()) }

                navigationKeywords.forEach { keyword ->
                    if (speechState.text.contains(keyword)) {
                        _uiState.update {
                            it.copy(
                                navigateTo = keyword
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            postUseCase.getPosts().collect { posts ->
                _uiState.value = _uiState.value.copy(posts = posts)
            }
        }
    }

    fun pulseBigHeart(pulse: Boolean) {
        _uiState.value = _uiState.value.copy(pulseBigHeart = pulse)
    }

    fun startListening() {
        if (!uiState.value.isListening) {
            speechToText.startListening()
        }
    }

    fun setCurrentIndex(
        index: Int,
    ) {
        _uiState.value = _uiState.value.copy(
            currentRouteIndex = index,
        )
    }

    fun setCurrentRoute(
        route: String
    ) {
        _uiState.value = _uiState.value.copy(
            navigateTo = route
        )
    }

    override fun onCleared() {
        super.onCleared()
        speechToText.close()
    }

    fun toggleShowCameraPreview() {
        _uiState.update {
            it.copy(
                showCameraPreview = !it.showCameraPreview
            )
        }
    }
}