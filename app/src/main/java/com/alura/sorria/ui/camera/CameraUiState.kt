package com.alura.sorria.ui.camera

import android.graphics.PointF
import android.graphics.Rect

data class CameraUiState(
    val mainText: String = "",
    val faceDimensions: Rect? = null,
    val smileProb: Float? = null,
    val isSmiling: Boolean = false,
    val rightEye: PointF? = null,
    val leftEye: PointF? = null,
    val nose: PointF? = null,
    val mouth: PointF? = null,
    val rotY: Float = 0f,
    val rotZ: Float = 0f,
    val rotX: Float = 0f
)
