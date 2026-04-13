package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.boostar_uaal.core.entities.Event
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.TypeMultimedia

@Composable
fun Event(event: Event, onTryArClick: () -> Unit){
    Column() {
        EventTitle(
            logo = event.logo,
            title = event.title
        )
        EventBanner(
            name = event.bannerName,
            description = event.bannerDescription,
            media = event.bannerMedia,
            isMain = event.isMain,
            onTryArClick = { onTryArClick() }
        )
        EventProduct(
            productDescription = event.productDescription,
            productImage = event.productImage,
            isLeft = true

        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewEvent(){
    val event = Event(
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
    )
    Event(event){}
}