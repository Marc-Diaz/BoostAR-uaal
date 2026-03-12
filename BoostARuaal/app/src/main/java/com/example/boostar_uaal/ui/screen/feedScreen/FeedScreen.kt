package com.example.boostar_uaal.ui.screen.feedScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.feedScreen.components.FeedItem


@Composable

fun FeedScreen(

    productId: Int,
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
    sortOrder: String

) {
    val viewModel = viewModel<FeedScreenViewModel>(
        key = sortOrder,
        factory = FeedScreenViewModelFactory(sortOrder)
    )
    val products by viewModel.products.collectAsState()
    val context = LocalContext.current

    val pagerState = rememberPagerState(pageCount = { products.size })

    LaunchedEffect(productId) {
        Log.d("Product Launch", "$productId")
        viewModel.initializeFeed(productId)
    }

    LaunchedEffect(productId, products.isNotEmpty()) {
        if (products.isNotEmpty()) {
            val pageIndex = products.indexOfFirst { it.id == productId }
            if (pageIndex != -1) {
                pagerState.scrollToPage(pageIndex)
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        if (products.isNotEmpty()) {
            if (pagerState.currentPage >= products.size - 2) {
                viewModel.loadNextPage()
            }
            if (pagerState.currentPage <= 1) {
                viewModel.loadPrevPage()
            }
        }

    }


    if (products.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { page -> products[page].id }
        ) { page ->
            val currentProduct = products[page]
            FeedItem(
                product = currentProduct,
                onPartnerClick = { },
                onShareClick = { },
                onCartClick = { },
                onDetailsClick = { },
                onTryArClick = { viewModel.onTryArClick(context, it) },
                onQuickPayClick = { },
                onLikeClick = {viewModel.toggleLike(it); Log.d("LIKE id", "$it")}
            )
        }
    }

}


