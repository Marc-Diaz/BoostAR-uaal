package com.example.boostar_uaal.ui.screen.feedScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.feedScreen.components.FeedItem


@Composable

fun FeedScreen(

    productId: Int?,
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
    val lastSelectedIndex by viewModel.lastSelectedIndex.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = lastSelectedIndex,
        pageCount = { products.size })
    Log.d("CurrentPage", "$lastSelectedIndex")

    LaunchedEffect(productId) {
        Log.d("Product Launch", "$productId")
        viewModel.initializeFeed(productId)
    }

    LaunchedEffect(pagerState.currentPage) {
        Log.d("Product pager", "${pagerState.currentPage}")
        if (products.isNotEmpty()) {
            viewModel.saveCurrentIndex(pagerState.currentPage)
            if (pagerState.currentPage >= products.size - 2) {
                viewModel.loadNextPage()
            }
            if (pagerState.currentPage <= 1) {
                viewModel.loadPrevPage()
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.FeedScreen(productId, sortOrder)
            )
        },
        content = { paddingValues ->
            Box(Modifier.padding(paddingValues)){
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
                            onCartClick = { viewModel.addProductToCart(currentProduct, 0, 0) },
                            onDetailsClick = { },
                            onTryArClick = {
                                navigateTo(Routes.ArScreen(
                                    lensId = currentProduct.model,
                                    grouLensId = "4ceedef4-a19f-47c9-a85b-08b97efda6c1")
                                )
                            },

                            onQuickPayClick = { },
                            onLikeClick = { viewModel.toggleLike(it); Log.d("LIKE id", "$it") }
                        )
                    }
                }
            }
        }
    )
}



