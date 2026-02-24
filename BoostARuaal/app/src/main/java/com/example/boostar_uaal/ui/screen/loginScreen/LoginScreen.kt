package com.example.boostar_uaal.ui.screen.loginScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.BoostArApplication.Companion.composeAuth
import com.example.boostar_uaal.R
import com.example.boostar_uaal.ui.screen.authScreen.components.AuthButton
import com.example.boostar_uaal.core.theme.secondaryButtonColor
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.authScreen.components.GoogleAuthButton
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult

@Composable
fun LogInScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit) {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(R.drawable.carrusel_auth_2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(40.dp)
                    ) {
                        Text(
                            "BoostAR",
                            color = secondaryButtonColor,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            "Log in the future.",
                            color = secondaryButtonColor,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    color = secondaryButtonColor
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .widthIn(max = 400.dp)
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            GoogleAuthButton(
                                composeAuth = composeAuth,
                                onResult = {
                                    when (it) {
                                        is NativeSignInResult.Success -> navigateTo(Routes.Authenticated)
                                        is NativeSignInResult.Error -> navigateTo(Routes.AuthScreen)
                                        else -> Log.d("ERROR", "$it")
                                    }
                                }
                            )
                            AuthButton(
                                onClick = { navigateTo(Routes.SignInScreen) },
                                text = "Continue with phone",
                                icon = R.drawable.phone_icon,
                                isFilled = true,
                            )
                            AuthButton(
                                onClick = { navigateTo(Routes.LogInScreen) },
                                text = "Continue with apple",
                                isFilled = false,
                            )

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


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 600.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            "BoostAR",
                            color = secondaryButtonColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            "Log in the future.",
                            color = secondaryButtonColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        color = secondaryButtonColor,
                        shape = RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, start = 30.dp, end = 30.dp, bottom = 32.dp)
                                .navigationBarsPadding(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            GoogleAuthButton(
                                composeAuth = composeAuth,
                                onResult = {
                                    when (it) {
                                        is NativeSignInResult.Success -> navigateTo(Routes.Authenticated)
                                        is NativeSignInResult.Error -> navigateTo(Routes.AuthScreen)
                                        else -> Log.d("ERROR", "$it")
                                    }
                                }
                            )
                            AuthButton(
                                onClick = { navigateTo(Routes.SignInScreen) },
                                text = "Continue with phone",
                                icon = R.drawable.phone_icon,
                                isFilled = true,
                            )
                            AuthButton(
                                onClick = { navigateTo(Routes.LogInScreen) },
                                text = "Continue with apple",
                                isFilled = false,
                            )
                        }
                    }
                }
            }
        }
    }
}




