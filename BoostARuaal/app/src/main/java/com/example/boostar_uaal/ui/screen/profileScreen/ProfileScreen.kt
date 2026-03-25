package com.example.boostar_uaal.ui.screen.profileScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.entities.Brand
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.entities.Style
import com.example.boostar_uaal.core.entities.TypeMultimedia
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.feedScreen.components.FeedItem
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    Scaffold(
        bottomBar = { BottomNavBar(navigateTo, Routes.ProfileScreen) },
        content = {

        }
    )



}