package com.example.boostar_uaal.ui.screen.feedScreen.components
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.Brand
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.entities.Style
import com.example.boostar_uaal.core.entities.TypeMultimedia
import com.example.boostar_uaal.core.theme.brandPrimaryColor
import com.example.boostar_uaal.core.theme.secondaryButtonColor

@Composable
fun ProductDetailsDialog(
    product: ProductDetail,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(horizontal = 10.dp, vertical = 24.dp)
        ) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    InterText(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.Black
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Marca: ${product.brand.name}",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = product.style.name,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                }
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = brandPrimaryColor),
                    shape = RoundedCornerShape(33.dp)
                ) {
                    InterText("Volver", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Preview
@Composable
fun ProductDetailDialogPreview(){
    val product = ProductDetail(
        id = 4,
        name = "Pantalón estampado de flores",
        price = 39.99,
        discountPrice = 20.20,
        brand = Brand(id = 1, name = "ITB"),
        style = Style(id = 1, name = "Casual"),
        numLikes = 2,
        isLiked = false,
        coverImage = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/pantalon_estampado_flores_p.jpg?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9wYW50YWxvbl9lc3RhbXBhZG9fZmxvcmVzX3AuanBnIiwiaWF0IjoxNzcyNjI4NTY3LCJleHAiOjQ4OTQ2OTI1Njd9.blp19FhADbW4rT9HSlm6EnwWcKmpxyKtr1Gtna7pAYc",
        multimedia = listOf(Multimedia(
            id = 1,
            multimediaURL = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/pantalon_estampado_flores_0.jpg?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9wYW50YWxvbl9lc3RhbXBhZG9fZmxvcmVzXzAuanBnIiwiaWF0IjoxNzcyNjI4NzUzLCJleHAiOjQ4OTQ2OTI3NTN9.rB7gzOTc5vxutkRSMLfLVfj4horGs1L77KWI4fXM9RE",
            type = TypeMultimedia.IMAGE
        )),
        sizes = emptyList(),
        colors = emptyList(),
        model = "50507980875",
        partner = Partner(
            id = "b834ef7e-6124-462c-9faf-68baa46841c0",
            name = "jordi Rodrigo",
            logoUrl = "https://lh3.googleusercontent.com/a/ACg8ocLUg8mkC_NRfr1N3VIzDQ7TfExrvIjOi16aKaq65WDkyanMUK-_=s96-c"
        )
    )
    ProductDetailsDialog(product) { }
}