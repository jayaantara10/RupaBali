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
fun EditProfileScreen(
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

    // User Nickname TextField State
    var nicknameTextFieldValue: String by rememberSaveable { mutableStateOf("") }
    var isNicknameTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var nicknameTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val nicknameTextFieldFocusRequester = FocusRequester()

    // User Description TextField State
    var descriptionTextFieldValue: String by rememberSaveable { mutableStateOf("") }
    var isDescriptionTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var descriptionTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val descriptionTextFieldFocusRequester = FocusRequester()

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

            CircleImagePicker(
                modifier = Modifier
                    .width(200.dp),
                icon = painterResource(id = R.drawable.ic_round_person_24),
                text = stringResource(id = R.string.label_input_user_photo_profile),
                imageUrl = ""
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            ImagePicker(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = painterResource(id = R.drawable.ic_round_insert_photo_24),
                text = stringResource(id = R.string.label_input_user_photo_background),
                imageUrl = ""
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nickname Text Field
            ShortTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = painterResource(id = R.drawable.ic_round_person_24),
                label = stringResource(id = R.string.label_user_nickname),
                text = nicknameTextFieldValue,
                isInputError = isNicknameTextFieldError,
                errorMessage = nicknameTextFieldErrorMessage,
                focusRequester = nicknameTextFieldFocusRequester,
                onInputChange = {
                    nicknameTextFieldValue = it
                }
            )

            // Description Text Field
            LongTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.description_title),
                text = descriptionTextFieldValue,
                isInputError = isDescriptionTextFieldError,
                errorMessage = descriptionTextFieldErrorMessage,
                focusRequester = descriptionTextFieldFocusRequester,
                onInputChange = {
                    descriptionTextFieldValue = it
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
        title = stringResource(id = R.string.edit_profile_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun EditProfileScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        EditProfileScreen(navController = rememberNavController())
    }
}
