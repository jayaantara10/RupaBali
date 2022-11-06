package id.jayaantara.rupabali.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.ui.theme.RupaBaliTheme
import id.jayaantara.rupabali.ui.theme.Typography

@Composable
fun CustomPositiveIconButton(icon: Int? = null, onClick: (() -> Unit)? = null) {
            Button(
                onClick = onClick!!,
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colors.primary)

            ) {
                if (icon != null) {
                    Icon(painter = painterResource(
                        id = icon),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = colors.background
                    )
                }
            }
}

@Composable
fun CustomPositiveTextButtonSmall(text: String? = null, onClick: (() -> Unit)? = null) {
    Button(
        onClick = onClick!!,
        modifier = Modifier
            .padding(0.dp)
            .height(48.dp)
            .widthIn(min = 96.dp),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.primary)

    ) {
        if (text != null) {
            Text(
                style = Typography.button,
                color = colors.background,
                text = text)
        }

    }
}

@Composable
fun CustomPositiveTextButtonFlex(text: String? = null, onClick: (() -> Unit)? = null) {
    Button(
        onClick = onClick!!,
        modifier = Modifier
            .padding(0.dp)
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.primary)

    ) {
        if (text != null) {
            Text(
                style = Typography.button,
                color = colors.background,
                text = text)
        }

    }
}

@Preview
@Composable
fun CustomPositiveIconButtonPreview() {
    RupaBaliTheme(darkTheme = false) {
        CustomPositiveIconButton(R.drawable.ic_round_arrow_forward_ios_24) {
            testLog()
        }
    }
}

@Preview
@Composable
fun CustomPositiveTextButtonFlexPreview() {
    RupaBaliTheme(darkTheme = false) {
        CustomPositiveTextButtonFlex("Test") {
            testLog()
        }
    }
}

fun testLog(){
    //Just for testing
}