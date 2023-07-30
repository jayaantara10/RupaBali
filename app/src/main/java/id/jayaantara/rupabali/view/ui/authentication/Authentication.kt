package id.jayaantara.rupabali.view.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
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

@Composable
fun AuthenticationScreen(
    navController: NavController
) {

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
            loginImageBackground,
            gradientOverlay,
            loginTitleText,
            loginDescriptionText,
            forgotPasswordText,
            clickHereClickableText,
            emailTextField,
            passwordTextField,
            loginButton,
            registerButton,
            loginByFacebookButton,
            loginByGmailButton,
            watermarkText
        ) = createRefs()

        // Guidelines
        val topGuideline = createGuidelineFromTop(0.3f)

        // Email TextField State
        val emailTextFieldLabel = stringResource(id = R.string.label_email)
        var emailTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        var isEmailTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
        var emailTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
        val emailTextFieldFocusRequester = FocusRequester()

        // Password TextField State
        val passwordTextFieldLabel = stringResource(id = R.string.label_password)
        var passwordTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        var isPasswordTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
        var passwordTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
        val passwordTextFieldFocusRequester = FocusRequester()

        //Button State
        //Login Button State
        val loginButtonLabel = stringResource(id = R.string.label_login)
        val isLoginButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isLoginButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //Register Button State
        val registerButtonLabel = stringResource(id = R.string.label_register)
        val isRegisterButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isRegisterButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //Login By Facebook Button State
        val loginByFacebookButtonIcon = R.drawable.ic_facebook
        val isLoginByFacebookButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isLoginByFacebookButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //Login By Gmail Button State
        val loginByGmailButtonIcon = R.drawable.ic_google
        val isLoginByGmailButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isLoginByGmailButtonEnable: Boolean by remember { mutableStateOf(true ) }

        // Login Image Background
        Image(
            modifier = Modifier.constrainAs(loginImageBackground){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            painter = painterResource(id = R.drawable.lukisan_2),
            contentDescription = "",
            contentScale = ContentScale.Inside
        )

        //Gradient Overlay
        Box(
            modifier = Modifier
                .constrainAs(gradientOverlay) {
                    top.linkTo(loginImageBackground.top)
                    bottom.linkTo(loginImageBackground.bottom)
                    start.linkTo(loginImageBackground.start)
                    end.linkTo(loginImageBackground.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        0.5f to Color.Transparent,
                        0.9f to MaterialTheme.colors.background,
                        1.0f to MaterialTheme.colors.background,
                    )
                )
        )

        // Login Title Text
        Text(
            modifier = Modifier
                .constrainAs(loginTitleText) {
                    top.linkTo(topGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            style = Typography.h1,
            maxLines = 1,
            text = stringResource(id = R.string.login_title)
        )

        // Login Description Text
        Text(
            modifier = Modifier
                .constrainAs(loginDescriptionText) {
                    top.linkTo(loginTitleText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            style = Typography.body1,
            maxLines = 1,
            text = stringResource(id = R.string.login_description)
        )

        // Email Text Field
        EmailTextField(
            modifier = Modifier
                .constrainAs(emailTextField) {
                    top.linkTo(loginDescriptionText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            label = emailTextFieldLabel,
            text = emailTextFieldValue,
            isInputError = isEmailTextFieldError,
            errorMessage = emailTextFieldErrorMessage,
            focusRequester = emailTextFieldFocusRequester,
            onInputChange = {
                emailTextFieldValue = it
                if (Validation.isEmpty(it)) {
                    isEmailTextFieldError= true
                    emailTextFieldErrorMessage = context.getString(R.string.error_empty_email)
                } else {
                    isEmailTextFieldError = false
                }
            },
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
                if (Validation.isEmpty(it)) {
                    isPasswordTextFieldError = true
                    passwordTextFieldErrorMessage = context.getString(R.string.error_empty_password)
                } else {
                    isPasswordTextFieldError = false
                }

            }
        )

        // Forgot Password Text
        Text(
            modifier = Modifier
                .constrainAs(forgotPasswordText) {
                    top.linkTo(passwordTextField.bottom)
                    end.linkTo(clickHereClickableText.start)
                }
                .padding(top = 4.dp, end = 4.dp),
            style = Typography.body2,
            maxLines = 1,
            text = stringResource(id = R.string.lupa_password)
        )

        // Click Here Clickable Text
        ClickableText(
            modifier = Modifier
                .constrainAs(clickHereClickableText) {
                    top.linkTo(passwordTextField.bottom)
                    end.linkTo(parent.end)
                }
                .padding(top = 4.dp, end = 32.dp),
            style = Typography.overline,
            maxLines = 1,
            text = AnnotatedString(stringResource(id = R.string.click_here)),
            onClick = {

            }
        )

        // Login Button
        PositiveTextButtonFlex(
            modifier = Modifier
                .constrainAs(loginButton) {
                    top.linkTo(forgotPasswordText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 16.dp)
                .wrapContentHeight(),
            text = loginButtonLabel,
            isVisible = isLoginButtonVisible,
            isEnable = isLoginButtonEnable
        ){
            if (Validation.isEmpty(emailTextFieldValue)) {
                isEmailTextFieldError = true
                emailTextFieldErrorMessage = context.getString(R.string.error_empty_email)
                emailTextFieldFocusRequester.requestFocus()
            } else if (Validation.isEmpty(passwordTextFieldValue)) {
                isPasswordTextFieldError = true
                passwordTextFieldErrorMessage = context.getString(R.string.error_empty_email)
                passwordTextFieldFocusRequester.requestFocus()
            } else {
                toMainActivity(navController)
            }
        }

        // Register Button
        PositiveTextButtonFlex(
            modifier = Modifier
                .constrainAs(registerButton) {
                    top.linkTo(loginButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(loginByFacebookButton.start)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 16.dp, bottom = 8.dp)
                .wrapContentHeight(),
            text = registerButtonLabel,
            isVisible = isRegisterButtonVisible,
            isEnable = isRegisterButtonEnable
        ){
            navToRegisterScreen(navController = navController)
        }

        // Login By Facebook Button
        PositiveIconButton(
            modifier = Modifier
                .constrainAs(loginByFacebookButton) {
                    top.linkTo(loginButton.bottom)
                    end.linkTo(loginByGmailButton.start)
                }
                .padding(end = 16.dp, bottom = 8.dp)
                .wrapContentSize(),
            icon = loginByFacebookButtonIcon,
            isVisible = isLoginByFacebookButtonVisible,
            isEnable = isLoginByFacebookButtonEnable
        ){
            loginByFacebook()
        }

        // Login By Gmail Button
        PositiveIconButton(
            modifier = Modifier
                .constrainAs(loginByGmailButton) {
                    top.linkTo(loginButton.bottom)
                    end.linkTo(parent.end)
                }
                .padding(end = 32.dp, bottom = 8.dp)
                .wrapContentSize(),
            icon = loginByGmailButtonIcon,
            isVisible = isLoginByGmailButtonVisible,
            isEnable = isLoginByGmailButtonEnable
        ){
            loginByGmail()
        }

        // Watermark Text
        Text(
            modifier = Modifier
                .constrainAs(watermarkText) {
                    top.linkTo(loginByFacebookButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 64.dp, bottom = 32.dp),
            style = Typography.caption,
            maxLines = 1,
            text = stringResource(id = R.string.app_name)
        )
    }
}

private fun navToRegisterScreen(navController: NavController){
    navController.navigate(Route.REGISTER_USER)
}

private fun loginByGmail() {

}

private fun loginByFacebook() {

}

private fun toMainActivity(navController: NavController) {
    navController.popBackStack()
    navController.navigate(Route.MAIN)
}

@Preview
@Composable
private fun AuthenticationScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        AuthenticationScreen(navController = rememberNavController())
    }
}