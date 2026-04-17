    package com.example.boostar_uaal.core.components
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyListScope
    import androidx.compose.foundation.lazy.LazyListState
    import androidx.compose.foundation.lazy.rememberLazyListState
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp

    @Composable
    fun LazyAdaptiveFeedLayout(
        modifier: Modifier = Modifier,
        state: LazyListState = rememberLazyListState(),
        backgroundColor: Color = Color.White,
        verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp),
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        content: LazyListScope.() -> Unit
    ) {

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                state = state,
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 840.dp)
                    .padding(bottom = 100.dp),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                content = content
            )
        }
    }