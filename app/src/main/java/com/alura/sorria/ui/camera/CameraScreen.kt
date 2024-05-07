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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.alura.sorria.ui.components.TextCustom
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceContour
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark


@OptIn(ExperimentalGetImage::class)
@Composable
fun CameraScreen(
    onSmiledDetected: () -> Unit,
    onSlideToTop: () -> Unit,
    onSlideToBottom: () -> Unit,
    onMouthIsOpened: () -> Unit
) {
    val viewModel = hiltViewModel<CameraViewModel>()
    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current.applicationContext

    LaunchedEffect(state.isSmiling) {
        if (state.isSmiling) {
            onSmiledDetected()
        }
    }

    LaunchedEffect(state.rotZ) {
        if (state.rotZ > 10) {
            onSlideToTop()
        }
        if (state.rotZ < -10) {
            onSlideToBottom()
        }
    }

    var centralPointUpperLip by remember { mutableFloatStateOf(0f) }
    var centralPointLowerLip by remember { mutableFloatStateOf(0f) }
    var mouthIsOpened by remember{ mutableStateOf(false)}
    mouthIsOpened = (centralPointLowerLip - centralPointUpperLip) > 75

    LaunchedEffect(mouthIsOpened) {
        if(mouthIsOpened){
            onMouthIsOpened()
        }
    }

    val highAccuracyOpts = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
        .build()

    val faceDetector = remember {
        FaceDetection.getClient(highAccuracyOpts)
    }

    val cameraAnalyzer = remember {
        CameraAnalyzer { imageProxy ->
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                faceDetector
                    .process(image)
                    .addOnSuccessListener { faces ->
                        faces.firstOrNull()?.let { face ->
                            viewModel.setFaceDimensions(face.boundingBox)
                            viewModel.setSmileProb(face.smilingProbability)

                            viewModel.setMainReferencePoints(
                                rightEye = face.getLandmark(FaceLandmark.RIGHT_EYE)?.position,
                                leftEye = face.getLandmark(FaceLandmark.LEFT_EYE)?.position,
                                nose = face.getLandmark(FaceLandmark.NOSE_BASE)?.position,
                                mouth = face.getLandmark(FaceLandmark.MOUTH_BOTTOM)?.position,
                                rotY = face.headEulerAngleY,
                                rotZ = face.headEulerAngleZ,
                                rotX = face.headEulerAngleX
                            )

                            face.getContour(FaceContour.UPPER_LIP_TOP)?.let { points: FaceContour ->
                                points.let {
                                    centralPointUpperLip = it.points[it.points.size / 2].y
                                }
                            }

                            face.getContour(FaceContour.LOWER_LIP_BOTTOM)?.points?.let {
                                centralPointLowerLip = it[it.size / 2].y
                            }

                            viewModel.setMainText("Distancia entre os labios: ${centralPointLowerLip - centralPointUpperLip}")
                        }
                    }
                    .addOnCompleteListener {
                        imageProxy.close()
                    }
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
        TestScreen(state = state)
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


