package com.alura.sorria.ui.camera

import android.graphics.PointF
import android.graphics.Rect
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        CameraUiState("Dimensões rosto")
    )
    var uiState = _uiState.asStateFlow()

    fun setFaceDimensions(faceDimensions: Rect) {
        _uiState.update {
            it.copy(faceDimensions = faceDimensions)
        }
    }

    fun setSmileProb(smileProb: Float?) {
        _uiState.update {
            it.copy(
                smileProb = smileProb,
            )
        }
    }

    fun setMainReferencePoints(
        rightEye: PointF?,
        leftEye: PointF?,
        nose: PointF?,
        mouth: PointF?,
        rotY: Float,
        rotZ: Float,
        rotX: Float
    ) {
        _uiState.update {
            it.copy(
                rightEye = rightEye,
                leftEye = leftEye,
                nose = nose,
                mouth = mouth,
                rotY = rotY,
                rotZ = rotZ,
                rotX = rotX
            )
        }
    }

    fun setMainText(value: String) {
        _uiState.update {
            it.copy(mainText = value)
        }
    }
}