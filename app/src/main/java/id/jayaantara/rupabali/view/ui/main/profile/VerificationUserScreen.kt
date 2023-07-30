package id.jayaantara.rupabali.view.ui.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import id.jayaantara.rupabali.utils.Validation
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.main.ProfileScreen
import id.jayaantara.rupabali.view.ui.main.artwork.*
import id.jayaantara.rupabali.view.ui.main.toBackScreen

@Composable
fun VerificationUserScreen(
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

    // User FullName TextField State
    var fullNameTextFieldValue: String by rememberSaveable { mutableStateOf("") }
    var isFullNameTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var fullNameTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val fullNameTextFieldFocusRequester = FocusRequester()

    // Type User Selector State
    var selectedUserTypeOptionId: Int by rememberSaveable { mutableStateOf(0) }
    var isUserTypeSelectorError: Boolean by rememberSaveable { mutableStateOf(false) }
    var userTypeSelectorErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val userTypeSelectorFocusRequester = FocusRequester()

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = 32.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(88.dp))

            // Page Title
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.h2,
                textAlign = TextAlign.Left,
                maxLines = 1,
                text = stringResource(id = R.string.verification_user_title)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Page Description Text
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.body1,
                textAlign = TextAlign.Justify,
                text = stringResource(id = R.string.verification_user_description)
            )

            Spacer(modifier = Modifier.height(16.dp))

            IdentityCardPicker(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = painterResource(id = R.drawable.ic_personal_id_card_rounded),
                text = stringResource(id = R.string.label_user_identity_card),
                imageUrl = ""
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Full Name Text Field
            ShortTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = painterResource(id = R.drawable.ic_round_person_24),
                label = stringResource(id = R.string.label_user_fullname),
                text = fullNameTextFieldValue,
                isInputError = isFullNameTextFieldError,
                errorMessage = fullNameTextFieldErrorMessage,
                focusRequester = fullNameTextFieldFocusRequester,
                onInputChange = {
                    fullNameTextFieldValue = it
                }
            )

            SimpleOptionSelector(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = painterResource(id = R.drawable.ic_round_verified_user_24),
                label = stringResource(id = R.string.label_user_type),
                selectedOptionId = selectedUserTypeOptionId,
                options = listOf(
                    OptionItem(id = 1, option = "Kolektor"),
                    OptionItem(id = 2, option = "Seniman"),
                ),
                isInputError = isUserTypeSelectorError,
                errorMessage = userTypeSelectorErrorMessage,
                focusRequester = userTypeSelectorFocusRequester,
                onOptionSelected = {
                    selectedUserTypeOptionId = it
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
                    text = stringResource(id = R.string.label_submit),
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
        title = stringResource(id = R.string.verification_user_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun VerificationUserScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        VerificationUserScreen(navController = rememberNavController())
    }
}
