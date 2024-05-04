package com.alura.sorria.speechToText

import android.content.Context
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SpeechToText @Inject constructor(
    private val context: Context
) : RecognitionListener {

    private val _state = MutableStateFlow(SpeechToTextState())
    val state = _state.asStateFlow()

    private val recognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    fun startListening(languageCode: String = "pt-BR") {
        _state.update { SpeechToTextState() }

        val isAvailable = SpeechRecognizer.isRecognitionAvailable(context)

        if (isAvailable) {
            val intent = android.content.Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
            }

            recognizer.setRecognitionListener(this)
            recognizer.startListening(intent)

            _state.update { it.copy(isListening = true) }

        } else {
            _state.update { it.copy(error = true) }
        }
    }

    fun close() {
        recognizer.stopListening()
        recognizer.destroy()
    }

    override fun onReadyForSpeech(params: android.os.Bundle?) {
        _state.update { it.copy(error = false) }
    }


    override fun onEndOfSpeech() {
        _state.update { it.copy(endOfSpeech = true) }
    }

    override fun onError(error: Int) {
        val textByCode = when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Erro de áudio"
            SpeechRecognizer.ERROR_CLIENT -> "Erro de cliente"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Permissões insuficientes"
            SpeechRecognizer.ERROR_NETWORK -> "Erro de rede"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Tempo de rede esgotado"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Reconhecedor ocupado"
            SpeechRecognizer.ERROR_SERVER -> "Erro de servidor"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT, SpeechRecognizer.ERROR_NO_MATCH -> "Nada detectado"
            else -> "Erro desconhecido"
        }

        val errorMessage =
            if (error == SpeechRecognizer.ERROR_SPEECH_TIMEOUT || error == SpeechRecognizer.ERROR_NO_MATCH) {
                "Tente novamente. $textByCode"
            } else {
                "Tente novamente. Erro: $textByCode"
            }

        _state.update {
            it.copy(error = true, text = errorMessage, endOfSpeech = true)
        }
    }

    override fun onResults(results: android.os.Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        matches?.firstOrNull()?.let { text ->
            _state.update { it.copy(text = text) }
        }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onPartialResults(partialResults: android.os.Bundle?) = Unit

    override fun onEvent(eventType: Int, params: android.os.Bundle?) = Unit
}