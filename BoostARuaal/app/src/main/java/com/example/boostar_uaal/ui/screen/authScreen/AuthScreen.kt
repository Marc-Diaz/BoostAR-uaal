package com.example.boostar_uaal.ui.screen.authScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.secondaryButtonColor
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton

@Composable
fun AuthScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    Image(
                        painter = painterResource(R.drawable.carrusel_auth_2),
                        contentDescription = "Auth Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(modifier = Modifier.align(Alignment.BottomStart).padding(40.dp)) {
                        InterText("BoostAR.", color = secondaryButtonColor, fontSize = 64.sp, fontWeight = FontWeight.ExtraBold)
                        InterText("Try it first.", color = secondaryButtonColor, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
                Surface(modifier = Modifier.weight(1f).fillMaxHeight(), color = secondaryButtonColor) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.widthIn(max = 400.dp).padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AuthButton(onClick = { navigateTo(Routes.LogInScreen) }, text = "Log in", isFilled = true)
                            AuthButton(onClick = { navigateTo(Routes.SignInScreen) }, text = "Sign in", isFilled = true)
                            AuthButton(onClick = { navigateTo(Routes.HomeScreen) }, text = "Enter as guest", isFilled = false)
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(R.drawable.carrusel_auth_2),
                    contentDescription = "Auth Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // 2. Columna que contiene los Textos y el Panel
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 600.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        InterText("BoostAR.", color = secondaryButtonColor, fontSize = 54.sp, fontWeight = FontWeight.ExtraBold)
                        InterText("Try it first.", color = secondaryButtonColor, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        color = secondaryButtonColor,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, start = 30.dp, end = 30.dp, bottom = 32.dp)
                                .navigationBarsPadding(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AuthButton(onClick = { navigateTo(Routes.LogInScreen) }, text = "Log in", isFilled = true)
                            AuthButton(onClick = { navigateTo(Routes.SignInScreen) }, text = "Sign in", isFilled = true)
                            AuthButton(onClick = { navigateTo(Routes.HomeScreen) }, text = "Enter as guest", isFilled = false)
                        }
                    }
                }
            }
        }
    }
}

