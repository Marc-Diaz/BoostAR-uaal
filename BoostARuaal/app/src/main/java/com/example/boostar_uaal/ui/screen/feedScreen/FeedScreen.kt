package com.example.boostar_uaal.ui.screen.feedScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.NavItem
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.feedScreen.components.FeedItem
import com.example.boostar_uaal.ui.screen.feedScreen.components.ProductDetailsDialog
import com.snap.camerakit.internal.fe

@Composable
fun FeedScreen(

    productId: Int?,
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
    sortOrder: String

) {
    val feedViewModel = viewModel<FeedScreenViewModel>(
        key = sortOrder,
        factory = FeedScreenViewModelFactory(sortOrder)
    )
    val products by feedViewModel.products.collectAsState()
    val lastSelectedIndex by feedViewModel.lastSelectedIndex.collectAsState()
    val showDialog by feedViewModel.showDialog.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = lastSelectedIndex,
        pageCount = { products.size })
    val context = LocalContext.current
    LaunchedEffect(productId) {
        Log.d("Product Launch", "$productId")

        feedViewModel.initializeFeed(productId)
    }

    LaunchedEffect(pagerState.currentPage) {
        Log.d("Product pager", "${pagerState.currentPage}")
        if (products.isNotEmpty()) {
            feedViewModel.saveCurrentIndex(pagerState.currentPage)
            if (pagerState.currentPage >= products.size - 2) {
                feedViewModel.loadNextPage()
            }
            if (pagerState.currentPage <= 1) {
                feedViewModel.loadPrevPage()
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = NavItem.Feeds.route
            )
        },
        content = { paddingValues ->
            Box(Modifier.padding(bottom = paddingValues.calculateBottomPadding())){
                if (products.isEmpty()) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color.White)
                    }
                } else {
                    VerticalPager(
                        state = pagerState,
                        key = { page -> products[page].id }
                    ) { page ->
                        val currentProduct = products[page]
                        FeedItem(
                            product = currentProduct,
                            onPartnerClick = { },
                            onShareClick = { feedViewModel.shareProduct(context) },
                            onCartClick = {  },
                            onDetailsClick = { feedViewModel.openDialog() },
                            onTryArClick = {
                                navigateTo(Routes.ArScreen(
                                    lensId = currentProduct.model,
                                    grouLensId = "4ceedef4-a19f-47c9-a85b-08b97efda6c1")
                                )
                            },
                            onQuickPayClick = { },
                            onLikeClick = { feedViewModel.toggleLike(it); Log.d("LIKE id", "$it") }
                        )
                        if (showDialog){
                            ProductDetailsDialog(
                                product = currentProduct,
                                onDismiss = {
                                    feedViewModel.closeDialog()
                                }
                            )
                        }
                    }
                }

            }
        }
    )
}

