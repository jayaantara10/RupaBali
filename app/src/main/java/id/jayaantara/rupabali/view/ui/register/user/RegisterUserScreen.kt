package id.jayaantara.rupabali.view.ui.register.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.utils.Validation
import id.jayaantara.rupabali.view.Route
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import kotlinx.coroutines.NonDisposableHandle.parent
import org.w3c.dom.Text

@Composable
fun RegisterUserScreen(
    navController: NavController
){
    val scrollState = rememberScrollState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(MaterialTheme.colors.background)
    ) {

        //Context
        val context = LocalContext.current

        // References
        val (
            topAppbar,
            descriptionText,
            usernameTextField,
            emailTextField,
            passwordTextField,
            confirmPasswordTextField,
            registerButton,
            cancelButton
        ) = createRefs()

        // Email TextField State
        val emailTextFieldLabel = stringResource(id = R.string.label_email)
        var emailTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        var isEmailTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
        var emailTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
        val emailTextFieldFocusRequester = FocusRequester()


        // Username TextField State
        val usernameTextFieldIcon = painterResource(id = R.drawable.ic_round_person_24)
        val usernameTextFieldLabel = stringResource(id = R.string.label_user_name)
        var usernameTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        var isUsernameTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
        var usernameTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
        val usernameTextFieldFocusRequester = FocusRequester()

        // Password TextField State
        val passwordTextFieldLabel = stringResource(id = R.string.label_password)
        var passwordTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        var isPasswordTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
        var passwordTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
        val passwordTextFieldFocusRequester = FocusRequester()

        // Confirm Password TextField State
        val confirmPasswordTextFieldLabel = stringResource(id = R.string.label_confirm_password)
        var confirmPasswordTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        var isConfirmPasswordTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
        var confirmPasswordTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
        val confirmPasswordTextFieldFocusRequester = FocusRequester()

        //Register Button State
        val registerButtonLabel = stringResource(id = R.string.label_register)
        val isRegisterButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isRegisterButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //Cancel Button State
        val cancelButtonLabel = stringResource(id = R.string.label_cancel)
        val isCancelButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isCancelButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //AlertDialog State
        var confirmDialogState: Boolean by rememberSaveable { mutableStateOf(false) }
        val confirmDialogTitle = stringResource(id = R.string.register_confirm_title)
        val confirmDialogDescription = stringResource(id = R.string.register_confirm_description)

        //TopAppbar
        SimpleTopAppBar(
            modifier = Modifier
                .constrainAs(topAppbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentHeight(),
            title = stringResource(id = R.string.register_user_title),
            isButtonBackVisible = true,
            onBackPress = {
                navToAuthenticationScreen(navController = navController)
            }
        )

        // Login Description Text
        Text(
            modifier = Modifier
                .constrainAs(descriptionText) {
                    top.linkTo(topAppbar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp, top = 16.dp),
            style = Typography.body1,
            maxLines = 1,
            text = stringResource(id = R.string.register_user_description)
        )

        // Username Text Field
        ShortTextField(
            modifier = Modifier
                .constrainAs(usernameTextField) {
                    top.linkTo(descriptionText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            icon = usernameTextFieldIcon,
            label = usernameTextFieldLabel,
            text = usernameTextFieldValue,
            isInputError = isUsernameTextFieldError,
            errorMessage = usernameTextFieldErrorMessage,
            focusRequester = usernameTextFieldFocusRequester,
            onInputChange = {
                usernameTextFieldValue= it
                if (Validation.isEmpty(usernameTextFieldValue)) {
                    isUsernameTextFieldError = true
                    usernameTextFieldErrorMessage = context.getString(R.string.error_empty_username)
                } else {
                    isUsernameTextFieldError = false
                }
            }
        )

        // Email Text Field
        EmailTextField(
            modifier = Modifier
                .constrainAs(emailTextField) {
                    top.linkTo(usernameTextField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            label = emailTextFieldLabel,
            text = emailTextFieldValue,
            isInputError = isEmailTextFieldError,
            errorMessage = emailTextFieldErrorMessage,
            focusRequester = emailTextFieldFocusRequester,
            onInputChange = {
                emailTextFieldValue = it
                if (!Validation.isValidEmail(emailTextFieldValue)) {
                    isEmailTextFieldError = true
                    emailTextFieldErrorMessage = context.getString(R.string.error_invalid_email)
                } else {
                    isEmailTextFieldError = false
                }

            }
        )

        // Password Text Field
        PasswordTextField(
            modifier = Modifier
                .constrainAs(passwordTextField) {
                    top.linkTo(emailTextField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            label = passwordTextFieldLabel,
            text = passwordTextFieldValue,
            isInputError = isPasswordTextFieldError,
            errorMessage = passwordTextFieldErrorMessage,
            focusRequester = passwordTextFieldFocusRequester,
            onInputChange = {
                passwordTextFieldValue = it
                if (!Validation.isValidPassword(passwordTextFieldValue)) {
                    isPasswordTextFieldError = true
                    passwordTextFieldErrorMessage = context.getString(R.string.error_invalid_password)
                } else {
                    isPasswordTextFieldError = false
                }

            }
        )


        // Confirm Password Text Field
        PasswordTextField(
            modifier = Modifier
                .constrainAs(confirmPasswordTextField) {
                    top.linkTo(passwordTextField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            label = confirmPasswordTextFieldLabel,
            text = confirmPasswordTextFieldValue,
            isInputError = isConfirmPasswordTextFieldError,
            errorMessage = confirmPasswordTextFieldErrorMessage,
            focusRequester = confirmPasswordTextFieldFocusRequester,
            onInputChange = {
                confirmPasswordTextFieldValue = it
                if (Validation.isEmpty(confirmPasswordTextFieldValue)) {
                    isConfirmPasswordTextFieldError = true
                    confirmPasswordTextFieldErrorMessage = context.getString(R.string.error_empty_confirm_password)
                } else {
                    isConfirmPasswordTextFieldError = false
                }
            }
        )


        // Cancel Button
        NegativeTextButtonFlex(
            modifier = Modifier
                .constrainAs(cancelButton) {
                    top.linkTo(confirmPasswordTextField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(registerButton.start)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 32.dp, start = 32.dp, end = 8.dp, bottom = 8.dp)
                .wrapContentHeight(),
            text = cancelButtonLabel,
            isVisible = isCancelButtonVisible,
            isEnable = isCancelButtonEnable
        ){

        }

        // Register Button
        PositiveTextButtonFlex(
            modifier = Modifier
                .constrainAs(registerButton) {
                    top.linkTo(confirmPasswordTextField.bottom)
                    start.linkTo(cancelButton.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 32.dp, start = 8.dp, end = 32.dp, bottom = 8.dp)
                .wrapContentHeight(),
            text = registerButtonLabel,
            isVisible = isRegisterButtonVisible,
            isEnable = isRegisterButtonEnable
        ){
            if (Validation.isEmpty(usernameTextFieldValue)) {
                isUsernameTextFieldError = true
                usernameTextFieldErrorMessage = context.getString(R.string.error_empty_username)
                usernameTextFieldFocusRequester.requestFocus()
            } else if (Validation.isEmpty(emailTextFieldValue)) {
                isEmailTextFieldError = true
                emailTextFieldErrorMessage = context.getString(R.string.error_empty_email)
                emailTextFieldFocusRequester.requestFocus()
            } else if (Validation.isEmpty(passwordTextFieldValue)) {
                isPasswordTextFieldError = true
                passwordTextFieldErrorMessage = context.getString(R.string.error_empty_password)
                passwordTextFieldFocusRequester.requestFocus()
            } else if (Validation.isEmpty(confirmPasswordTextFieldValue)) {
                isConfirmPasswordTextFieldError = true
                confirmPasswordTextFieldErrorMessage = context.getString(R.string.error_empty_confirm_password)
                confirmPasswordTextFieldFocusRequester.requestFocus()
            } else if (!Validation.isConfirmPasswordSame(password= passwordTextFieldValue, confirmPassword= confirmPasswordTextFieldValue)) {
                isConfirmPasswordTextFieldError = true
                confirmPasswordTextFieldErrorMessage = context.getString(R.string.error_different_confirm_password)
                confirmPasswordTextFieldFocusRequester.requestFocus()
            } else {
                confirmDialogState = true
            }

        }

        ConfirmationAlertDialog(
            title = confirmDialogTitle,
            description = confirmDialogDescription,
            dialogState = confirmDialogState,
            onDismissRequest = {
                confirmDialogState = !it
            },
            onConfirmButtonClick = {

            }
        )
    }
}

fun navToAuthenticationScreen(navController: NavController) {
    navController.navigate(Route.AUTHENTICATION){
        popUpTo(Route.AUTHENTICATION){
            inclusive = true
        }
    }
}

@Preview
@Composable
private fun RegisterUserScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        RegisterUserScreen(navController = rememberNavController())
    }
}