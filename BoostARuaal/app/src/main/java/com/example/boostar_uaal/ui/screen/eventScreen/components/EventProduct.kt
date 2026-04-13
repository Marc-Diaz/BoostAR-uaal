package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.ItemImage

@Composable
fun EventProduct(modifier: Modifier = Modifier, productDescription: String, productImage: String, isLeft: Boolean){
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(0.85f),
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            ItemImage(
                url =  productImage,
                contentDescription = "Imagen del producto",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = productDescription,
                color = Color.Black,
                fontSize = 13.sp,
                lineHeight = 18.sp
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