package id.jayaantara.rupabali.view.components

import android.view.MenuItem
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.theme.Brown
import id.jayaantara.rupabali.view.theme.DarkBrown
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun SimpleTopAppBar(
    modifier: Modifier,
    title: String,
    isButtonBackVisible: Boolean = false,
    isButtonOptionVisible: Boolean = false,
    onBackPress: () -> Unit = {},
    menuList: Array<String> = arrayOf(""),
    onMenuClick: ((selectedIndex: Int) -> Unit) = {}
) {

    var showMenu: Boolean by remember { mutableStateOf(false) }
    val bottomEndRadius: Dp by animateDpAsState(if (showMenu) 0.dp else 12.dp)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            bottomStart = 12.dp, bottomEnd = bottomEndRadius),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = AppBarDefaults.TopAppBarElevation
    ){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
        ) {
            // References
            val (
                titleText,
                backButton,
                optionButton
            ) = createRefs()

            // Back Button
            Box(
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        height = Dimension.fillToConstraints
                    }
                    .width(24.dp),
            ) {
                if (isButtonBackVisible) {
                    IconButton(
                        onClick = onBackPress,
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_round_arrow_back_ios_24
                            ),
                            contentDescription = "",
                            tint = MaterialTheme.colors.background
                        )
                    }
                }
            }

            // Activity Title Text
            Text(
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backButton.top)
                        bottom.linkTo(backButton.bottom)
                        start.linkTo(backButton.end)
                        end.linkTo(optionButton.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(start = 16.dp, end = 16.dp),
                style = Typography.h3,
                color = MaterialTheme.colors.background,
                textAlign = TextAlign.Center,
                maxLines = 1,
                text = title
            )

            // Option Button
            Box(
                modifier = Modifier
                    .constrainAs(optionButton) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
                    .width(24.dp),
            ) {
                if (isButtonOptionVisible) {

                    IconButton(
                        onClick = {showMenu = !showMenu},
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_round_menu_24
                            ),
                            contentDescription = "",
                            tint = MaterialTheme.colors.background
                        )
                    }

                    MaterialTheme(
                        colors = MaterialTheme.colors.copy(surface = MaterialTheme.colors.primary),
                        shapes = MaterialTheme.shapes.copy(
                            medium = RoundedCornerShape(
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                    ) {
                        DropdownMenu(
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary),
                            offset = DpOffset(x = (-32).dp, y = 16.dp),
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            menuList.forEachIndexed { index ,menu ->
                                DropdownMenuItem(
                                    onClick = {
                                        showMenu = false
                                        onMenuClick(index)
                                    }
                                ) {
                                    Text(
                                        text = menu,
                                        style = Typography.body1,
                                        color = MaterialTheme.colors.background,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionTopAppBar(
    modifier: Modifier,
    title: String,
    isButtonBackVisible: Boolean = false,
    isButtonActionVisible: Boolean = false,
    buttonActionIcon: Int = R.drawable.ic_round_menu_24,
    onBackPress: () -> Unit = {},
    onActionPress: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = AppBarDefaults.TopAppBarElevation
    ){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
        ) {
            // References
            val (
                titleText,
                backButton,
                optionButton
            ) = createRefs()

            // Back Button
            Box(
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        height = Dimension.fillToConstraints
                    }
                    .width(24.dp),
            ) {
                if (isButtonBackVisible) {
                    IconButton(
                        onClick = onBackPress,
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_round_arrow_back_ios_24
                            ),
                            contentDescription = "",
                            tint = MaterialTheme.colors.background
                        )
                    }
                }
            }

            // Activity Title Text
            Text(
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backButton.top)
                        bottom.linkTo(backButton.bottom)
                        start.linkTo(backButton.end)
                        end.linkTo(optionButton.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(start = 16.dp, end = 16.dp),
                style = Typography.h3,
                color = MaterialTheme.colors.background,
                textAlign = TextAlign.Center,
                maxLines = 1,
                text = title
            )

            // Option Button
            Box(
                modifier = Modifier
                    .constrainAs(optionButton) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
                    .width(24.dp),
            ) {
                if (isButtonActionVisible) {
                    IconButton(
                        onClick = onActionPress,
                    ) {
                        Icon(
                            painter = painterResource(
                                id = buttonActionIcon
                            ),
                            contentDescription = "",
                            tint = MaterialTheme.colors.background
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBottomBar(
    modifier: Modifier,
    hint : String,
    text : String,
    onInputChange : (String) -> Unit,
    onEmojiClick : () -> Unit,
    onAttachClick : () -> Unit,
    onSendClick : () -> Unit
){
    val color = MaterialTheme.colors.onPrimary

    Box(
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val (
                chatEditText,
                sendButton
            ) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(chatEditText) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(sendButton.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(end = 8.dp)
            ){
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp, max = Dp.Infinity)
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp)
                ){

                    val (
                        leadingIcon,
                        textField,
                        trailingIcon
                    ) = createRefs()

                    IconButton(
                        modifier = Modifier
                            .constrainAs(leadingIcon) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            }
                            .size(24.dp),
                        onClick = onEmojiClick
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_emoji_emotions_24),
                            tint = color,
                            contentDescription = "",
                        )
                    }

                    BasicTextField(
                        modifier = Modifier
                            .constrainAs(textField) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(leadingIcon.end)
                                end.linkTo(trailingIcon.start)
                                width = Dimension.fillToConstraints
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        value = text,
                        onValueChange = onInputChange,
                        decorationBox = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (text.isEmpty()){
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterVertically),
                                        style = Typography.body1.copy(
                                            color = color
                                        ),
                                        text = hint
                                    )
                                }
                            }
                            it()
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        cursorBrush = Brush.verticalGradient(
                            0.00f to color,
                            0.35f to color,
                            0.35f to color,
                            0.90f to color,
                            0.90f to color,
                            1.00f to color
                        ),
                        maxLines = 5,
                        textStyle = Typography.body1.copy(
                            color = color,
                        )
                    )

                    IconButton(
                        modifier = Modifier
                            .constrainAs(trailingIcon) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                            .size(24.dp),
                        onClick = onAttachClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_attach_file_24),
                            tint = color,
                            contentDescription = "",
                        )
                    }
                }
            }

            Button(
                modifier = Modifier
                    .constrainAs(sendButton) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .size(48.dp)
                    .padding(0.dp),
                onClick = onSendClick,
                contentPadding = PaddingValues(8.dp),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary)

            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_round_send_24,
                    ),
                    contentDescription = "",
                    tint = MaterialTheme.colors.background
                )
            }

        }

    }
}

@Preview
@Composable
fun AppBarPreview() {
    RupaBaliTheme(darkTheme = false) {
        val menuList = stringArrayResource(id = R.array.label_menu_profile)

        SimpleTopAppBar(
            modifier = Modifier,
            title = "Test",
            isButtonBackVisible = true,
            isButtonOptionVisible = true,
            onBackPress = {},
            menuList = menuList,
            onMenuClick = { selectedMenuIndex ->
                when (selectedMenuIndex) {
                    0 -> {

                    }
                    1 -> {

                    }
                }
            }
        )
    }
}

