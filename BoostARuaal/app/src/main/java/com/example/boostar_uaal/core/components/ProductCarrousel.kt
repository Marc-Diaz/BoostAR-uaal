package com.example.boostar_uaal.core.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.core.entities.Product



/**
 * Carrusel horizontal deslizable para presentar una colección de productos del catálogo.
 *
 * Este componente agrupa múltiples tarjetas de producto (`ProductCard`) en una fila navegable.
 * Al igual que otros carruseles del proyecto, utiliza renderizado diferido (`LazyRow`) para
 * garantizar un rendimiento fluido y un bajo consumo de memoria, reciclando los elementos
 * a medida que el usuario hace scroll horizontal.
 *
 * @param products Lista de objetos `Product` a renderizar. Si está vacía, se mostrarán
 * componentes de carga (skeletons).
 * @param onItemClick Callback que se ejecuta al pulsar sobre el cuerpo principal de una tarjeta.
 * Emite el `id` del producto asociado.
 * @param onLikeClick Callback que se ejecuta al interactuar con el botón de favoritos de una
 * tarjeta específica. Emite el `id` del producto asociado.
 */
@Composable
fun ProductCarrousel(
    products: List<Product>,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (!products.isEmpty())
                items(products) { product ->
                    ProductCard(
                        product = product,
                        clickable = { onItemClick(product.id) },
                        onLikeClick = { onLikeClick(product.id) }
                    )
                }
            else {
                items(10) {
                    BlankProductCard()
                }
            }
        }
    }
}