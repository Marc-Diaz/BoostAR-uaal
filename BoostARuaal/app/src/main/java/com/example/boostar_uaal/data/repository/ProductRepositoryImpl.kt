package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.entities.Brand
import com.example.boostar_uaal.core.entities.ClothingSize
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.core.entities.Product
import com.example.boostar_uaal.core.entities.ProductColor
import com.example.boostar_uaal.core.entities.Standard
import com.example.boostar_uaal.core.entities.Style
import com.example.boostar_uaal.core.entities.TypeMultimedia
import com.example.boostar_uaal.core.utils.ColorFormatter

class ProductRepositoryImpl(){
    val brands = listOf(
        Brand(
            id = 0,
            name = "Marca 1",
            logoImage = ""
        ),
        Brand(
            id = 1,
            name = "Marca 2",
            logoImage = ""
        )
    )

    val colors = listOf(
        ProductColor(
            id = 0,
            name = "Red",
            color = ColorFormatter().getColor("#FF0000")
        ),
        ProductColor(
            id = 1,
            name = "Green",
            color = ColorFormatter().getColor("#00FF00")
        )
    )
    val styles = listOf(
        Style(
            id = 0,
            name = "Formal",
        ),
        Style(
            id = 1,
            name = "Informal"
        )
    )
    val standard = listOf(
        Standard(
            id = 0,
            name = "Alfabetico"
        ),
        Standard(
            id = 1,
            name = "Numerico"
        )
    )
    val tallas = listOf(
        ClothingSize(
            id = 0,
            name = "S",
            standard = standard[0],
        ),
        ClothingSize(
            id = 1,
            name = "M",
            standard = standard[0],
        ),
        ClothingSize(
            id = 2,
            name = "L",
            standard = standard[0],
        ),
        ClothingSize(
            id = 3,
            name = "XL",
            standard = standard[0],
        ),
    )
    val products = listOf(
        Product(
            id = 0,
            name = "Camiseta 1",
            price = 10.25,
            discountPrice = 10.00,
            style = styles[0],
            tallas = tallas.filter { it.standard.id == 0 },
            colors = colors,
            brand = brands[0],
            numLikes = 4309,
            modelURL = "",
            coverImage = "https://www.joma-sport.com/dw/image/v2/BFRV_PRD/on/demandware.static/-/Sites-joma-masterCatalog/default/dw0e82cc45/images/medium/101739.100_1.jpg?sw=900&sh=900&sm=fit",
            multimedia = listOf(
                Multimedia(
                    id = 0,
                    multimediaURL = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/feed1.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9mZWVkMS5wbmciLCJpYXQiOjE3NzAyOTIyNjcsImV4cCI6MTc3MDg5NzA2N30.QZeTFDDXWAxz-wsmDVKMDQwt55UMXgrb3i4q_S5INtI",
                    isPrincipal = true,
                    type = TypeMultimedia.IMAGE
                ),
                Multimedia(
                    id = 1,
                    multimediaURL = "https://mundotextil.com/tienda/454-large_default/camiseta-fruit-of-the-loom-610360.jpg",
                    isPrincipal = true,
                    type = TypeMultimedia.IMAGE
                )
            )
        ),
        Product(
            id = 0,
            name = "Camiseta 1",
            price = 10.25,
            discountPrice = 5.4,
            brand = brands[1],
            style = styles[1],
            tallas = tallas.filter { it.standard.id == 1 },
            colors = colors,
            numLikes = 4309,
            modelURL = "",
            coverImage = "https://www.joma-sport.com/dw/image/v2/BFRV_PRD/on/demandware.static/-/Sites-joma-masterCatalog/default/dw0e82cc45/images/medium/101739.100_1.jpg?sw=900&sh=900&sm=fit",
            multimedia = listOf(
                Multimedia(
                    id = 0,
                    multimediaURL = "https://www.joma-sport.com/dw/image/v2/BFRV_PRD/on/demandware.static/-/Sites-joma-masterCatalog/default/dw0e82cc45/images/medium/101739.100_1.jpg?sw=900&sh=900&sm=fit",
                    isPrincipal = true,
                    type = TypeMultimedia.IMAGE
                ),
                Multimedia(
                    id = 1,
                    multimediaURL = "https://mundotextil.com/tienda/454-large_default/camiseta-fruit-of-the-loom-610360.jpg",
                    isPrincipal = true,
                    type = TypeMultimedia.IMAGE
                )
            )
        ),
        Product(
            id = 1,
            name = "Camiseta 2",
            price = 10.25,
            brand = brands[1],
            style = styles[1],
            tallas = tallas.filter { it.standard.id == 1 },
            colors = colors,
            numLikes = 4309,
            modelURL = "",
            coverImage = "https://www.joma-sport.com/dw/image/v2/BFRV_PRD/on/demandware.static/-/Sites-joma-masterCatalog/default/dw0e82cc45/images/medium/101739.100_1.jpg?sw=900&sh=900&sm=fit",
            multimedia = listOf(
                Multimedia(
                    id = 0,
                    multimediaURL = "https://www.joma-sport.com/dw/image/v2/BFRV_PRD/on/demandware.static/-/Sites-joma-masterCatalog/default/dw0e82cc45/images/medium/101739.100_1.jpg?sw=900&sh=900&sm=fit",
                    isPrincipal = true,
                    type = TypeMultimedia.IMAGE
                ),
                Multimedia(
                    id = 1,
                    multimediaURL = "https://mundotextil.com/tienda/454-large_default/camiseta-fruit-of-the-loom-610360.jpg",
                    isPrincipal = true,
                    type = TypeMultimedia.IMAGE
                )
            )
        ))
    fun getMockProducts(): List<Product> {
        return products
    }

    fun getMockProductById(id: Int): Product? {
        return products.find { it.id == id }

    }
}