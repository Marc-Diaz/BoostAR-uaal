package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.BackgorundImage
import com.example.boostar_uaal.core.entities.Brand
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.entities.Style
import com.example.boostar_uaal.core.entities.TypeMultimedia

@Composable
fun FeedItem(
    product: ProductDetail,
    onPartnerClick: () -> Unit,
    onShareClick: () -> Unit,
    onCartClick: (Int) -> Unit,
    onDetailsClick: () -> Unit,
    onTryArClick: () -> Unit,
    onLikeClick: (Int) -> Unit,
    onQuickPayClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { product.multimedia.size })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HorizontalPager(state = pagerState) { page ->
            BackgorundImage(
                url = product.multimedia[page].multimediaURL,
                contentDescription = product.name
            )
        }

        TopPartnerSearch (
            modifier = Modifier
                .statusBarsPadding(),
            partner = product.partner,
            onPartnerClick = onPartnerClick,
            onShareClick = onShareClick
        )

        RightSideBar(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 104.dp, end = 10.dp),
            isLiked = product.isLiked,
            onCartClick = {onCartClick(product.id)},
            onLikeClick = { onLikeClick(product.id) }
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
        ) {

            ProductInfoSection(
                modifier = Modifier.padding(horizontal = 24.dp),
                name = product.name,
                price = product.price,
                discountPrice = product.discountPrice
            )

            BottomActionDock(
                modifier = Modifier.fillMaxWidth(),
                onDetailsClick = onDetailsClick,
                onTryArClick = { onTryArClick() },
                onQuickPayClick = onQuickPayClick
            )
        }
    }
}

@Preview
@Composable
fun FeedItemPreview(){
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
    FeedItem(
        product = product,
        onPartnerClick = {  },
        onShareClick = {  },
        onCartClick = {  },
        onDetailsClick = {  },
        onTryArClick = {  },
        onLikeClick = {  },
        onQuickPayClick = {  }
    )
}