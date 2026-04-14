package com.example.boostar_uaal.ui.screen.eventScreen.components
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun EventProduct(modifier: Modifier = Modifier, productDescription: String, productImage: String, isLeft: Boolean){
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (isLeft){
            EventProductCard(
                modifier = Modifier.weight(1f),
                productImage = productImage
            )

            Spacer(modifier = Modifier.width(8.dp))
            EventProductDescription(
                modifier = Modifier.weight(1f),
                productDescription = productDescription
            )
        }
        else{
            EventProductDescription(
                modifier = Modifier.weight(1f),
                productDescription = productDescription
            )

            Spacer(modifier = Modifier.width(8.dp))
            EventProductCard(
                modifier = Modifier.weight(1f),
                productImage = productImage
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewEventProduct(){
    val imgage = ""
    val description = "Esta camiseta representa un momento icónico donde la música, la cultura y el espectáculo se unen en un escenario"
    EventProduct(
        productDescription = description,
        productImage = imgage,
        isLeft = true
    )
}