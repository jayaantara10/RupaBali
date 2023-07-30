package id.jayaantara.rupabali.view.components

import android.view.MenuItem
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.window.DialogProperties
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
fun ConfirmationAlertDialog(
    title: String,
    description: String,
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit,
    onConfirmButtonClick: () -> Unit
){

    if (dialogState) {
        AlertDialog(
            backgroundColor = MaterialTheme.colors.background,
            onDismissRequest = {
                onDismissRequest(dialogState)
            },
            title = null,
            text = null,
            buttons = {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // References
                    val (
                        titleText,
                        descriptionText,
                        noButton,
                        yesButton
                    ) = createRefs()

                    // Title Text
                    Text(
                        modifier = Modifier
                            .constrainAs(titleText) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            },
                        style = Typography.h3,
                        textAlign = TextAlign.Left,
                        maxLines = 1,
                        text = title
                    )

                    // Description Text
                    Text(
                        modifier = Modifier
                            .constrainAs(descriptionText) {
                                top.linkTo(titleText.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 16.dp),
                        style = Typography.body2,
                        textAlign = TextAlign.Left,
                        maxLines = 5,
                        text = description
                    )

                    // No Button
                    Box(
                        modifier = Modifier
                            .constrainAs(noButton) {
                                top.linkTo(descriptionText.bottom)
                                end.linkTo(yesButton.start)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 16.dp)
                            .wrapContentSize()
                    ) {
                        OutlinedButton(
                            onClick = {
                                onDismissRequest(dialogState)
                            },
                            enabled = true,
                            modifier = Modifier
                                .height(40.dp)
                                .width(72.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = colors.primary),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent
                            )

                        ) {
                            Text(
                                style = Typography.button,
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.primary,
                                text = stringResource(id = R.string.label_no)
                            )
                        }
                    }

                    // Yes Button
                    Box(
                        modifier = Modifier
                            .constrainAs(yesButton) {
                                top.linkTo(descriptionText.bottom)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 16.dp, start = 16.dp)
                            .wrapContentSize()
                    ) {
                        Button(
                            onClick = onConfirmButtonClick,
                            enabled = true,
                            modifier = Modifier
                                .height(40.dp)
                                .width(72.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary)

                        ) {
                            Text(
                                style = Typography.button,
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.background,
                                text = stringResource(id = R.string.label_yes)
                            )
                        }
                    }

                }

            },
            shape = RoundedCornerShape(12.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
fun CustomInputDialog(
    title: String,
    description: String,
    inputLayout: @Composable () -> Unit,
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit,
    onConfirmButtonClick: () -> Unit
){

    if (dialogState) {
        AlertDialog(
            backgroundColor = MaterialTheme.colors.background,
            onDismissRequest = {
                onDismissRequest(dialogState)
            },
            title = null,
            text = null,
            buttons = {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // References
                    val (
                        titleText,
                        descriptionText,
                        noButton,
                        yesButton
                    ) = createRefs()

                    // Title Text
                    Text(
                        modifier = Modifier
                            .constrainAs(titleText) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            },
                        style = Typography.h3,
                        textAlign = TextAlign.Left,
                        maxLines = 1,
                        text = title
                    )

                    // Description Text
                    Text(
                        modifier = Modifier
                            .constrainAs(descriptionText) {
                                top.linkTo(titleText.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 16.dp),
                        style = Typography.body2,
                        textAlign = TextAlign.Left,
                        maxLines = 5,
                        text = description
                    )

                    // Yes Button
                    Box(
                        modifier = Modifier
                            .constrainAs(yesButton) {
                                top.linkTo(descriptionText.bottom)
                                end.linkTo(noButton.start)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 16.dp)
                            .wrapContentSize()
                    ) {
                        Button(
                            onClick = onConfirmButtonClick,
                            enabled = true,
                            modifier = Modifier
                                .height(40.dp)
                                .width(72.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary)

                        ) {
                            Text(
                                style = Typography.button,
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.background,
                                text = stringResource(id = R.string.label_yes)
                            )
                        }
                    }

                    // No Button
                    Box(
                        modifier = Modifier
                            .constrainAs(noButton) {
                                top.linkTo(descriptionText.bottom)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 16.dp, start = 16.dp)
                            .wrapContentSize()
                    ) {
                        OutlinedButton(
                            onClick = {
                                onDismissRequest(dialogState)
                            },
                            enabled = true,
                            modifier = Modifier
                                .height(40.dp)
                                .width(72.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = colors.primary),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent
                            )

                        ) {
                            Text(
                                style = Typography.button,
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.primary,
                                text = stringResource(id = R.string.label_no)
                            )
                        }
                    }
                }

            },
            shape = RoundedCornerShape(12.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            )
        )
    }
}

@Preview
@Composable
fun AlertDialogPreview() {
    RupaBaliTheme(darkTheme = true) {
        var dialogState: Boolean by rememberSaveable { mutableStateOf(true) }
        ConfirmationAlertDialog(
            title = "Test App Bar",
            description = "Its test for appbar Its test for appbar Its test for appbar",
            dialogState = dialogState,
            onDismissRequest = {
                dialogState = !it
            },
            onConfirmButtonClick = {}
        )
    }
}