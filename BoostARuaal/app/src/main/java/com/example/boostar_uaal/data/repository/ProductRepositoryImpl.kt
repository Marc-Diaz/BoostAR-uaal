package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.data.models.ProductFilters
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * Implementación del repositorio que gestiona el catálogo de productos, delegando
 * las consultas complejas a funciones RPC (Remote Procedure Calls) de Supabase.
 */
class ProductRepositoryImpl(private val postgrest: Postgrest): ProductRepository  {

    private val feedPageSize = 2
    private val carrouselPageSize = 10

    /**
     * Recupera los detalles completos de un producto único mediante su identificador.
     *
     * @param id Identificador único numérico del producto a consultar.
     * @return Objeto `ProductDetail` con toda la información extendida del producto.
     */
    override suspend fun getProductById(id: Int): ProductDetail {
        val response = postgrest.rpc(
            function = "get_product_detail",
            parameters = buildJsonObject {
                put("p_product_id", id)
            }
        ).decodeAs<ProductDetail>()

        return response
    }

    /**
     * Obtiene una lista paginada de productos en formato básico.
     * Aplica filtros, ordenación y límite de resultados. Ideal para carruseles.
     *
     * @param sortMode Criterio de ordenación a aplicar en la consulta (ver `SortOrder`).
     * @param filters Objeto `ProductFilters` con las condiciones de búsqueda (ej. solo descuentos).
     * @param refId Identificador de referencia (cursor) para la paginación. Nulo para la primera página.
     * @param limit Cantidad máxima de elementos a devolver. Si es nulo, usa el valor por defecto (`carrouselPageSize`).
     * @return Lista de objetos `Product` que cumplen los criterios especificados.
     */

    override suspend fun getProducts(
        sortMode: String,
        filters: ProductFilters,
        refId: Int?,
        limit: Int?
    ): List<Product> {
        return try {
            val pageSize = limit ?: carrouselPageSize
            val response = postgrest.rpc(
                function = "get_products",
                parameters = buildJsonObject {
                    put("p_sort_mode", sortMode)
                    if (refId != null) put("p_ref_id", refId) else put("p_ref_id", JsonNull)
                    put("p_only_discounts", filters.discount)
                    if (filters.partnerId != null) put("p_partner_id", filters.partnerId) else put("p_partner_id", JsonNull)
                    put("p_page_size", pageSize)
                }
            ).decodeList<Product>()

            response
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching feed products: ${e.message}")
            emptyList()
        }
    }
    /**
     * Obtiene una lista paginada de productos en formato detallado, optimizada
     * para flujos de scroll infinito continuo soportando navegación bidireccional.
     *
     * @param sortMode Criterio de ordenación del feed.
     * @param filters Filtros aplicables a los productos del feed.
     * @param refId Identificador del producto pivote desde el cual buscar hacia adelante o atrás.
     * @param direction Dirección de carga del scroll (ej. "next", "prev") respecto al `refId`.
     * @return Lista de objetos `ProductDetail` con medios completos para renderizar en el feed.
     */

    override suspend fun getFeedProducts(
        sortMode: String,
        filters: ProductFilters,
        refId: Int?,
        direction: String,
    ): List<ProductDetail> {
        return try {
            val response = postgrest.rpc(
                function = "get_feed_products",
                parameters = buildJsonObject {
                    put("p_sort_mode", sortMode)
                    put("p_only_discounts", filters.discount)
                    if (refId != null) put("p_ref_id", refId) else put("p_ref_id", JsonNull)
                    put("p_direction", direction)
                    if (filters.partnerId != null) put("p_partner_id", filters.partnerId) else put("p_partner_id", JsonNull)
                    put("p_page_size", feedPageSize)
                }
            ).decodeList<ProductDetail>()
            response
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Obtiene una selección inicial de productos y los agrupa en bloques
     * temáticos para el flujo de configuración inicial del usuario.
     *
     * @return Lista de objetos `OnboardingStep`, cada uno conteniendo una categoría (tops, bottoms, etc.) y sus opciones.
     */
    override suspend fun getOnboardingSteps(): List<OnboardingStep> {
        val response: List<Product> = postgrest.rpc(
            function = "get_onboarding_steps"
        ).decodeList<Product>()

        var steps = response
        steps = steps + steps
        return listOf(
            OnboardingStep(
                id = "tops",
                title = "Escoge las opciones que te gusten.",
                options = steps.subList(0,4)
            ),
            OnboardingStep(
                id = "bottoms",
                title = "Escoge las opciones que te gusten.",
                options = steps.subList(4,8)
            ),
            OnboardingStep(
                id = "outerwear",
                title = "Escoge las opciones que te gusten.",
                options = steps.subList(8,12)
            )
        )
    }

}