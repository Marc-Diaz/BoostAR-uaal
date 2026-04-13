package com.example.boostar_uaal.ui.screen.profileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.LocalAuthState
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.grayTextColor
import com.example.boostar_uaal.core.utils.AuthState
import com.example.boostar_uaal.ui.screen.authScreen.AuthViewModel
import com.example.boostar_uaal.ui.screen.profileScreen.components.ProfileHeader
import com.example.boostar_uaal.ui.screen.profileScreen.components.StatusCard

@Composable
fun ProfileScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    val profileScreenViewModel = viewModel<ProfileScreenViewModel>()
    val authViewModel = viewModel<AuthViewModel>()
    val user by profileScreenViewModel.user.collectAsState()
    val cardData by profileScreenViewModel.cardData.collectAsState()
    val authState = LocalAuthState.current
    LaunchedEffect(Unit) {
        if (authState == AuthState.Authenticated)
            profileScreenViewModel.initializeProfileScreen()
    }
    Scaffold(
        bottomBar = { BottomNavBar(navigateTo, Routes.ProfileScreen) },
        content = { paddingValues ->
            AdaptiveFeedLayout {
                if (authState == AuthState.Authenticated){
                    ProfileHeader(
                        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                        name = user?.name ?: "",
                        mail = user?.mail ?: "",
                        picture = user?.picture ?:"https://lh3.googleusercontent.com/a/ACg8ocJHT7IO5QH47WjEdV4XTGqSIk_6-oO2pN3HNxGXcUDSF7cZgIQ=s96-c",
                        level = 12
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(cardData) { card ->
                            StatusCard(cardData = card)
                        }
                    }
                    Button(
                        onClick = {
                            authViewModel.signOut( onSuccess = {
                                navigateTo(Routes.AuthScreen)
                            })
                        },
                        modifier = Modifier.size(width = 323.dp, height = 57.dp).align(Alignment.CenterHorizontally),
                        content = {
                            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                                InterText(
                                    text = "Cerrar sessión",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 18.sp,
                                    color = Color.Red,
                                    fontWeight = FontWeight.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors
                            (containerColor = Color.White),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = SolidColor(Color.Red)
                        )
                    )
                }
                else{
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        InterText(
                            text = "Inicia se   sión",
                            color = grayTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    )



}