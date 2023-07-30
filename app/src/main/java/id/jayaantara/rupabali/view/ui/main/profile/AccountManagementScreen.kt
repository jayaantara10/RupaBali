package id.jayaantara.rupabali.view.ui.main.profile

import android.view.Menu
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.main.MainRoute
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun AccountManagementScreen(
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

            // Menu Management Profile
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.h3.copy(color = MaterialTheme.colors.onBackground),
                maxLines = 1,
                text = AnnotatedString(text = stringArrayResource(id = R.array.management_account_menu_label)[0])
            ){
                toEditProfileScreen(navController = navController)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Menu Management Profile
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.h3.copy(color = MaterialTheme.colors.onBackground),
                maxLines = 1,
                text = AnnotatedString(text = stringArrayResource(id = R.array.management_account_menu_label)[1])
            ){
                toChangePasswordScreen(navController = navController)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Menu Management Profile
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.h3.copy(color = MaterialTheme.colors.onBackground),
                maxLines = 1,
                text = AnnotatedString(text = stringArrayResource(id = R.array.management_account_menu_label)[2])
            ){
                toVerificationUserScreen(navController = navController)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.primary, thickness = 2.dp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Menu Management Profile
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.h3.copy(color = MaterialTheme.colors.secondary),
                maxLines = 1,
                text = AnnotatedString(text = stringArrayResource(id = R.array.management_account_menu_label)[3])
            ){

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Menu Management Profile
            ClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                style = Typography.overline.copy(color = Color.Red),
                maxLines = 1,
                text = AnnotatedString(text = stringArrayResource(id = R.array.management_account_menu_label)[4])
            ) {

            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.management_account_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

private fun toEditProfileScreen(navController: NavController){
    navController.navigate(MainRoute.EDIT_PROFILE)
}

private fun toChangePasswordScreen(navController: NavController){
    navController.navigate(MainRoute.CHANGE_PASSWORD)
}

private fun toVerificationUserScreen(navController: NavController){
    navController.navigate(MainRoute.USER_VERIFICATION)
}

@ExperimentalPagerApi
@Preview
@Composable
private fun AccountManagementScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        AccountManagementScreen(navController = rememberNavController())
    }
}