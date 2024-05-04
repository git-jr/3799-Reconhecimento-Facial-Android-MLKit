package com.alura.sorria.speechToText

data class SpeechToTextState(
    val text: String = "",
    val isListening: Boolean = false,
    val error: Boolean = false,
    val endOfSpeech: Boolean = false
)