package id.jayaantara.rupabali.view.ui.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.main.toBackScreen

@Composable
fun ChangePasswordScreen(
    navController: NavController
){

    val localDensity = LocalDensity.current
    var buttonLayoutHeight by remember {
        mutableStateOf(0.dp)
    }

    //Context
    val context = LocalContext.current

    val scrollState = rememberScrollState()
    val isEndOfForm = scrollState.value == scrollState.maxValue

    var pageState: Int by rememberSaveable { mutableStateOf(0) }

    // Old Password TextField State
    var oldPasswordTextFieldValue: String by rememberSaveable { mutableStateOf("") }
    var isOldPasswordTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var oldPasswordTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val oldPasswordTextFieldFocusRequester = FocusRequester()

    // New Password TextField State
    var newPasswordTextFieldValue: String by rememberSaveable { mutableStateOf("") }
    var isNewPasswordTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var newPasswordTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val newPasswordTextFieldFocusRequester = FocusRequester()

    // Confirmation New Password TextField State
    var confirmationNewPasswordTextFieldValue: String by rememberSaveable { mutableStateOf("") }
    var isConfirmationNewPasswordTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var confirmationNewPasswordTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val confirmationNewPasswordTextFieldFocusRequester = FocusRequester()

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = 32.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(88.dp))

            // Old Password Text Field
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.label_input_password),
                text = oldPasswordTextFieldValue,
                isInputError = isOldPasswordTextFieldError,
                errorMessage = oldPasswordTextFieldErrorMessage,
                focusRequester = oldPasswordTextFieldFocusRequester,
                onInputChange = {
                    oldPasswordTextFieldValue = it
                }
            )

            // New Password Text Field
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.label_input_new_password),
                text = newPasswordTextFieldValue,
                isInputError = isNewPasswordTextFieldError,
                errorMessage = newPasswordTextFieldErrorMessage,
                focusRequester = newPasswordTextFieldFocusRequester,
                onInputChange = {
                    newPasswordTextFieldValue = it
                }
            )

            // Confirmation New Password Text Field
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.label_input_confirmation_new_password),
                text = confirmationNewPasswordTextFieldValue,
                isInputError = isConfirmationNewPasswordTextFieldError,
                errorMessage = confirmationNewPasswordTextFieldErrorMessage,
                focusRequester = confirmationNewPasswordTextFieldFocusRequester,
                onInputChange = {
                    confirmationNewPasswordTextFieldValue = it
                }
            )

            Spacer(modifier = Modifier.height(32.dp))


            Row(modifier = Modifier
                .fillMaxWidth(),
            ) {
                // Cancel Button
                NegativeTextButtonFlex(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    text = stringResource(id = R.string.label_cancel),
                    isVisible = true,
                    isEnable = true
                ){

                }

                Spacer(modifier = Modifier.width(16.dp))

                // Next Button
                PositiveTextButtonFlex(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    text = stringResource(id = R.string.label_save),
                    isVisible = true,
                    isEnable = true
                ){

                }
            }

            Spacer(modifier = Modifier.height(32.dp))


        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.change_password_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ChangePasswordScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ChangePasswordScreen(navController = rememberNavController())
    }
}
