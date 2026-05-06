package com.example.boostar_uaal.ui.screen.fashionScreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.boostar_uaal.core.components.BlankProductCard
import com.example.core.entities.Product
/**
 * Cuadrícula fluida que renderiza la sección de productos más recientes.
 *
 * CONTEXTO TÉCNICO Y DE INTERFAZ:
 * - Disposición Automática: Emplea `FlowRow` configurado con un límite estricto de 2 elementos
 * por fila (`maxItemsInEachRow = 2`). Esto crea un efecto de cuadrícula sin la necesidad de
 * configurar un `LazyVerticalGrid` complejo, lo cual es ideal si este componente se incrusta
 * dentro de una pantalla que ya tiene su propio scroll vertical.
 * - Estado de Carga (Skeleton) Integrado: Actúa de forma condicional. Si la lista de productos
 * recibida está vacía, el componente no colapsa ni desaparece; en su lugar, renderiza
 * de inmediato 4 tarjetas en blanco (`BlankProductCard`) para indicar al usuario que el
 * contenido está en proceso de carga.
 *
 * @param products Lista del catálogo de novedades. Si está vacía, dispara la animación de carga.
 * @param onClick Acción de navegación que emite el ID único del producto al tocar su tarjeta.
 * @param onLikeClick Acción que registra la interacción de "Me gusta", emitiendo el ID correspondiente.
 * @param modifier Modificador base para el contenedor general del grupo.
 */
@Composable
fun NewestProductGrid(products: List<Product>, onClick: (Int) -> Unit, onLikeClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        maxItemsInEachRow = 2,
        modifier = modifier.fillMaxWidth()
    ) {
        if (products.isEmpty()) {
            repeat(4) {
                BlankProductCard(
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            products.forEach { product ->
                NewestProductCard(
                    product = product,
                    onClick = { onClick(product.id) },
                    onLikeClick = { onLikeClick(product.id) }
                )
            }
        }
    }
}