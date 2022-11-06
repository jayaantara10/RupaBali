package id.jayaantara.rupabali.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.ui.theme.RupaBaliTheme
import id.jayaantara.rupabali.ui.theme.Typography

class TextFieldState{
    var textState: String by mutableStateOf("")
    var isInputError: Boolean by mutableStateOf(false)
}

@Composable
fun EmailTextField(emailState: TextFieldState = remember{ TextFieldState() }) {

    val color =
        if (emailState.isInputError) {
            Color.Red
        } else {
            MaterialTheme.colors.primary
        }

    val trailingIcon =
        if (emailState.isInputError) {
            R.drawable.ic_round_error_24
        } else {
            null
        }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Row(
                modifier = Modifier
                    .height(24.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = Typography.body1,
                    color = MaterialTheme.colors.primary,
                    text = stringResource(id = R.string.label_email)
                )
            }

        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_round_email_24),
                contentDescription = "",
                tint = color

            )
        },
        trailingIcon = {
            if (trailingIcon != null){
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(24.dp),
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "",
                    tint = color

                )
            }
        },
        shape = RoundedCornerShape(30),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = color,
            focusedBorderColor = color

        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = true,
        ),
        textStyle = Typography.body1,
        value = emailState.textState,
        onValueChange = {
            emailState.textState = it
        },

    )
}

@Preview
@Composable
fun CustomTextFieldPreview(){
    RupaBaliTheme(darkTheme = false) {
        val emailState = remember { TextFieldState() }

        EmailTextField(
            emailState = emailState
        )

    }
}
