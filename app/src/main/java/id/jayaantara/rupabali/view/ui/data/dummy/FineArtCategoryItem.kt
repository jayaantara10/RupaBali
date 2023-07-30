package id.jayaantara.rupabali.view.ui.data.dummy

import androidx.compose.ui.graphics.painter.Painter

data class FineArtCategoryItem (
    val category: String,
    val imageUrl: String
)

val fineArtCategoryItems = listOf<FineArtCategoryItem>(
    FineArtCategoryItem(
        category = "Lukis",
        imageUrl = "https://www.dictio.id/uploads/db3342/original/3X/7/f/7f5e20719e3d85982c2c4793173e56468bd3977d.jpg"
    ),

    FineArtCategoryItem(
        category = "Rerajahan",
        imageUrl = "https://denpasarnow.com/wp-content/uploads/2021/08/kekereb2.jpg"
    ),

    FineArtCategoryItem(
        category = "Ukiran",
        imageUrl = "https://t-3.tstatic.net/jualbeli/img/njajal/2022/2/Menilik-Keindahan-Kerajinan-Ukiran-Kayu-Khas-Bali--Terkenal-Hingga-Mancanegara-master-537067029.jpg"
    ),

    FineArtCategoryItem(
        category = "Textil",
        imageUrl = "https://tetamian.com/wp-content/uploads/2018/04/Endek-Wajik-Ukir-Bali.jpg"
    ),
)
