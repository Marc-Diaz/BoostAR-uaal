package com.example.boostar_uaal.ui.screen.profileScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.core.entities.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.boostar_uaal.BoostArApplication.Companion.userRepository
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryColor
import kotlinx.coroutines.flow.asStateFlow

class ProfileScreenViewModel: ViewModel() {
    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user = _user.asStateFlow()
    private var _cardData: MutableStateFlow<List<StatusCardData>> = MutableStateFlow(emptyList())
    val cardData = _cardData.asStateFlow()
    fun initializeProfileScreen(){
        loadUser()
    }

    fun loadUser(){
        viewModelScope.launch{
            _user.value = userRepository.getUserProfile()
            loadCardData()
        }
    }

    fun loadCardData(){
        val cardData = listOf(
            StatusCardData(
                title = "Pedidos",
                icon = R.drawable.buys_icon,
                color = primaryColor,
                count = _user.value?.numBuys ?: 0
            ),
            StatusCardData(
                title = "Notificaciones",
                icon = R.drawable.notification_icon,
                count = _user.value?.numNotifications ?: 0,
                color = primaryColor
            ),
            StatusCardData(
                title = "Devoluciones",
                icon = R.drawable.refuns_icon,
                color = primaryColor,
                count = _user.value?.numRefunds ?: 0
            ),
            StatusCardData(
                title = "Likelist",
                icon = R.drawable.heart_icon,
                color = Color.Red,
                count = _user.value?.numLikes ?: 0
            )
        )
        _cardData.value = cardData
    }
}