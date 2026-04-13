package com.example.boostar_uaal.ui.screen.eventScreen

import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.core.entities.Event
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.TypeMultimedia

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventScreenViewModel: ViewModel() {
    private var _events: MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    fun initializeEvents(){
        loadEvents()
    }
    fun loadEvents(){
        val events = listOf(
            Event(
                id = 1,
                isMain = true,
                title = "Bad Bunny.",
                bannerName = "¡Bad Bunny!",
                bannerDescription = "¡Prueba la camiseta que llevó Bad Bunny en la super bowl!",
                logo = "",
                bannerMedia = Multimedia(
                    id = 11,
                    multimediaURL = "",
                    type = TypeMultimedia.VIDEO
                ),
                productImage = "",
                productDescription = "Esta camiseta representa un momento icónico donde la música, la cultura y el espectáculo se unen en un escenario global.\nUn símbolo de impacto que trasciende el evento y conecta a millones de personas.",
                model = ""
            ),
            Event(
                id = 2,
                isMain = false,
                logo = "",
                title = "Boostar.",
                bannerName = "El encuentro",
                bannerDescription = "¡Prueba la camiseta de las entidades desarrolladoras de Boostar!",
                bannerMedia = Multimedia(
                    id = 12,
                    multimediaURL = "",
                    type = TypeMultimedia.IMAGE
                ),
                productImage = "",
                productDescription = "Esta camiseta representa la unión de las entidades que forman parte de Boostar, impulsando la innovación y la conexión de talento dentro del ecosistema tecnológico y creativo.",
                model = ""
            )
        )
        _events.value = events
    }
}