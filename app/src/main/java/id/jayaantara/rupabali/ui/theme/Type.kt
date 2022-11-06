package id.jayaantara.rupabali.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.jayaantara.rupabali.R

val PoppinsRegular = FontFamily(Font(R.font.poppins_regular))
val PoppinsSemiBold = FontFamily(Font(R.font.poppins_semi_bold))
val PlayfairDisplayBold = FontFamily(Font(R.font.playfair_display_bold))

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = PlayfairDisplayBold,
        fontSize = 32.sp
    ),

    h2 = TextStyle(
        fontFamily = PlayfairDisplayBold,
        fontSize = 24.sp
    ),

    h3 = TextStyle(
        fontFamily = PlayfairDisplayBold,
        fontSize = 16.sp
    ),

    body1 = TextStyle(
        fontFamily = PoppinsRegular,
        fontSize = 16.sp
    ),

    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
        fontSize = 16.sp,
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)