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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.utils.CameraKitConfig
import com.example.boostar_uaal.ui.screen.arScreen.components.CameraControlsOverlay
import com.example.boostar_uaal.ui.screen.arScreen.components.ProductInformationTopBar
import com.example.boostar_uaal.ui.screen.feedScreen.FeedScreenViewModel
import com.example.boostar_uaal.ui.screen.feedScreen.components.ProductDetailsDialog
import com.snap.camerakit.lenses.LensesComponent
import com.snap.camerakit.lenses.whenHasFirst
import java.util.UUID

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ArScreen(
    back: () -> Unit,
    feedUuid: String?,
    lensId: String,
    onPermissionDenied: () -> Unit = {}
) {
    val arScreenViewModel = viewModel<ArScreenViewModel>()
    val feedViewModel = viewModel<FeedScreenViewModel>(key = feedUuid.toString())
    val product by feedViewModel.currentProduct.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val lensGroupId = CameraKitConfig.getDefaultGroupId(context)
    val imageProcessorSource = remember {
        CameraXImageProcessorSource(context = context, lifecycleOwner = lifecycleOwner)
    }
    val cameraKitSession = remember { mutableStateOf<Session?>(null) }
    val facingFront by arScreenViewModel.facingFront.collectAsState()

    LaunchedEffect(Unit) {
        feedViewModel.getCurrentProduct()
    }
    DisposableEffect(Unit) {
        onDispose { cameraKitSession.value?.close() }
    }

    Scaffold { paddingValues ->
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
                Column(
                    modifier = Modifier.padding(paddingValues).fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                   if(product != null){
                        ProductInformationTopBar(
                            partner = product!!.partner,
                            productName = product!!.name,
                            productLikes = product!!.numLikes,
                        )
                        CameraControlsOverlay(
                            onFlipCamera = { arScreenViewModel.flipCamera()},
                            onBack = { back() }
                        )
                    }
                }
            }
            cameraPermissionState.status.shouldShowRationale -> onPermissionDenied()
            else -> LaunchedEffect(Unit) { cameraPermissionState.launchPermissionRequest() }
        }

    }
}