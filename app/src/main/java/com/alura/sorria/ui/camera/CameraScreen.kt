package com.alura.sorria.ui.camera

import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.alura.sorria.ui.components.TextCustom
import com.google.mlkit.vision.common.InputImage


@OptIn(ExperimentalGetImage::class)
@Composable
fun CameraScreen() {
    val viewModel = hiltViewModel<CameraViewModel>()
    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current.applicationContext

    val cameraAnalyzer = remember {
        CameraAnalyzer { imageProxy ->
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            }
        }
    }

    // 1 Camera Controller
    val cameraController = remember {
        LifecycleCameraController(context).apply {
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context), cameraAnalyzer
            )
        }
    }


    // 2 Camera Preview
    CameraPreview(cameraController = cameraController)

    // 3 Overlay
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.faceDimensions?.let { faceDimensions ->
            TextCustom(
                text = "Dimensões rosto: $faceDimensions",
            )
        }
        state.smileProb?.let {
            TextCustom(
                text = "Probabilidade de sorriso: $it",
            )
        }
    }
}


@Composable
private fun TestScreen(
    state: CameraUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextCustom(
            text = state.mainText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        state.let {
            TextCustom("Olho direito: ${it.rightEye}")
            TextCustom("Olho esquerdo: ${it.leftEye}")

            TextCustom("Nariz: ${it.nose}")

            TextCustom("Boca: ${it.mouth}")

            TextCustom("Rotação horizontal: ${it.rotY}")
            TextCustom("Rotação vertical: ${it.rotZ}")
            TextCustom("Rotação frontal: ${it.rotX}")
        }
    }
}


