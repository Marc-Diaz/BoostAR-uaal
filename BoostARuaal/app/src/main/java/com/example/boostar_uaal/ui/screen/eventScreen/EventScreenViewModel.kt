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
                title = "Bad Bunny<span style=\"color:#007AFF\">.</span>",
                bannerName = "¡Bad Bunny!",
                bannerDescription = "¡Prueba la camiseta que llevó Bad Bunny en la super bowl!",
                logo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/logo_bad_bunny.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9sb2dvX2JhZF9idW5ueS5wbmciLCJpYXQiOjE3NzYxNTEwODQsImV4cCI6MTgwNzY4NzA4NH0.cni6XOVznVQSthyNKnB0cNEPuiwZwuKCeJH1Ll7y1JQ",
                bannerMedia = Multimedia(
                    id = 11,
                    multimediaURL = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/videos/banner_evento_bad_bunny.mp4?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJ2aWRlb3MvYmFubmVyX2V2ZW50b19iYWRfYnVubnkubXA0IiwiaWF0IjoxNzc2MTU0NTQ3LCJleHAiOjE4MDc2OTA1NDd9.xW-KfdFq6jglYh390GK00dSeT5fQ5CuolDFbt0-7SjI",
                    type = TypeMultimedia.VIDEO
                ),
                productImage = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/producto_evento_bad_bunny.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9wcm9kdWN0b19ldmVudG9fYmFkX2J1bm55LnBuZyIsImlhdCI6MTc3NjE1MjU4OSwiZXhwIjoxODA3Njg4NTg5fQ.DYxLY36vw74BMXiTL1ZmgPzimL0PVIYD8eGimYLOxC0",
                productDescription = "Esta camiseta representa un momento icónico donde la música, la cultura y el espectáculo se unen en un escenario global.<br><br>Un símbolo de impacto que trasciende el evento y conecta a millones de personas.",
                model = "",
                isProductImageLeft = true
            ),
            Event(
                id = 2,
                isMain = false,
                logo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/boostar_logo.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9ib29zdGFyX2xvZ28ucG5nIiwiaWF0IjoxNzc2MTUxMjEzLCJleHAiOjE4MDc2ODcyMTN9.qw4mar10wHNU3YGqpO_axQaijggiDEEuISdD10yCxWs",
                title = "Boostar<span style=\"color:#007AFF\">.</span>",
                bannerName = "El encuentro",
                bannerDescription = "¡Prueba la camiseta de las entidades desarrolladoras de Boostar!",
                bannerMedia = Multimedia(
                    id = 12,
                    multimediaURL = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/banner_evento_boostar.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9iYW5uZXJfZXZlbnRvX2Jvb3N0YXIucG5nIiwiaWF0IjoxNzc2MTUyMzkzLCJleHAiOjE4MDc2ODgzOTN9.eyjwuDMb8O7stlhsanfqOed1BiLj60fgGm48CTI7R5E",
                    type = TypeMultimedia.IMAGE
                ),
                productImage = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/producto_evento_boostar.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9wcm9kdWN0b19ldmVudG9fYm9vc3Rhci5wbmciLCJpYXQiOjE3NzYxNTI1MDcsImV4cCI6MTgwNzY4ODUwN30.939LRurrRWvxpMwIAGb67lBbFVy2zZ1nYNkKMdmbgQw",
                productDescription = "Esta camiseta representa la unión de las entidades que forman parte de Boostar, impulsando la innovación y la conexión de talento dentro del ecosistema tecnológico y creativo.",
                model = "",
                isProductImageLeft = false
            )
        )
        _events.value = events
    }
}