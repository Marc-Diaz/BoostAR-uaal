package com.example.boostar_uaal.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.example.boostar_uaal.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.boostar_uaal.core.utils.glow

@Composable
fun AuthLayout(
    title: String,
    subtitle: String,
    onBackClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()) {
                    MediaPlayer(context, R.raw.video_auth)

                    if (onBackClick != null) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(24.dp)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }

                    Column(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(40.dp)) {
                        Text(
                            title,
                            color = Color.White,
                            fontSize = 64.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            subtitle,
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Surface(modifier = Modifier
                    .weight(1f)
                    .glow(
                        color = primaryButtonColor,
                        blurRadius = 30.dp
                    )
                    .fillMaxHeight(), color = Color.White) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .widthIn(max = 400.dp)
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            content()
                        }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                MediaPlayer(context, R.raw.video_auth)
                if (onBackClick != null) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 32.dp, start = 16.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 600.dp)) {
                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                        Text(
                            title,
                            color = Color.White,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            subtitle,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .glow(
                                color = primaryButtonColor,
                                blurRadius = 30.dp
                            )
                            .wrapContentHeight(),
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, start = 30.dp, end = 30.dp, bottom = 40.dp)
                                .navigationBarsPadding(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun LayoutPreview(){
    AuthLayout(
        title = "Boostar",
        subtitle = "Try it first.",
        onBackClick = null,
        content = { }
    )
}