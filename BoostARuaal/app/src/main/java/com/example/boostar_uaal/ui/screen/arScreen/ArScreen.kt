package com.example.boostar_uaal.ui.screen.arScreen

import android.view.LayoutInflater


import android.view.ViewStub
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.snap.camerakit.Session
import com.snap.camerakit.invoke
import com.snap.camerakit.support.camerax.CameraXImageProcessorSource
import android.Manifest
import android.util.Log
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.utils.CameraKitConfig
import com.snap.camerakit.lenses.LensesComponent
import com.snap.camerakit.lenses.whenHasFirst

// CameraKitScreen.kt
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ArScreen(
    back: () -> Unit,
    lensId: String,
    onPermissionDenied: () -> Unit = {}
) {
    val arScreenViewModel = viewModel<ArScreenViewModel>()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val lensGroupId = CameraKitConfig.getDefaultGroupId(context)
    val imageProcessorSource = remember {
        CameraXImageProcessorSource(context = context, lifecycleOwner = lifecycleOwner)
    }
    val cameraKitSession = remember { mutableStateOf<Session?>(null) }
    val facingFront by arScreenViewModel.facingFront.collectAsState()

    DisposableEffect(Unit) {
        onDispose { cameraKitSession.value?.close() }
    }

    Scaffold(){ paddingValues ->
        when {
            cameraPermissionState.status.isGranted -> {
                imageProcessorSource.startPreview(facingFront)
                AndroidView(
                    factory = { ctx ->
                        LayoutInflater.from(ctx).inflate(R.layout.camera_layout, null).apply {
                            val viewStub = findViewById<ViewStub>(R.id.camera_kit_stub)

                            cameraKitSession.value = Session(context = ctx) {
                                imageProcessorSource(imageProcessorSource)
                                attachTo(viewStub)
                            }.apply {
                                lenses.repository.observe(
                                    LensesComponent.Repository.QueryCriteria.ById(
                                        id = lensId.trim(),
                                        groupId = lensGroupId.trim(),
                                    )
                                ) { result ->
                                    result.whenHasFirst { requestedLens ->
                                        lenses.processor.apply(requestedLens)
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
                Box(modifier = Modifier.padding(paddingValues)){
                    IconButton(
                        onClick = { arScreenViewModel.toggleCamera() },
                        content = { Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Arrow Back",
                            tint = Color.Gray
                        ) }
                    )
                }
            }
            cameraPermissionState.status.shouldShowRationale -> onPermissionDenied()
            else -> LaunchedEffect(Unit) { cameraPermissionState.launchPermissionRequest() }
        }

    }
}