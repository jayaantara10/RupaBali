package id.jayaantara.rupabali.view.ui.main.artwork

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.utils.Validation
import id.jayaantara.rupabali.utils.isScrolledToTheEnd
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ArtworkSubmissionScreen(
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

    // Artwork Title TextField State
    var titleTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artist TextField State
    var artistTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Creation Date Picker State
    var selectedDateValue: String by rememberSaveable { mutableStateOf("") }

    // Fine Art Category Selector State
    var selectedFineArtCategoryOptionId: Int by rememberSaveable { mutableStateOf(0) }

    // Artwork Category Selector State
    var selectedArtworkCategoryOptionId: Int by rememberSaveable { mutableStateOf(0) }

    // Scared Status Selector State
    var selectedScaredStatusOptionId: Int by rememberSaveable { mutableStateOf(0) }

    // Artwork Value TextField State
    var artworkValueTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Description TextField State
    var artworkDescriptionTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Function TextField State
    var artworkFunctionTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Height TextField State
    var heightTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Width TextField State
    var widthTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Long TextField State
    var longTextFieldValue: String by rememberSaveable { mutableStateOf("") }

    // Artwork Status Selector State
    var selectedArtworkStatusOptionId: Int by rememberSaveable { mutableStateOf(0) }

    // Link TextField State
    var linkTextFieldValue: String by rememberSaveable { mutableStateOf("") }

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

            SubmissionArtworkProgressBar(
                modifier = Modifier
                    .fillMaxWidth(),
                iconProgress = listOf(
                    painterResource(id = R.drawable.ic_round_insert_photo_24),
                    painterResource(id = R.drawable.ic_round_photo_size_select_large_24),
                    painterResource(id = R.drawable.ic_round_video_call_24),
                    painterResource(id = R.drawable.ic_round_add_location_24),
                    painterResource(id = R.drawable.ic_round_gavel_24),
                    painterResource(id = R.drawable.ic_round_document_scanner_24),
                    painterResource(id = R.drawable.ic_round_photo_library_24),
                    painterResource(id = R.drawable.ic_round_person_24),
                    painterResource(id = R.drawable.ic_round_star_24),
                    painterResource(id = R.drawable.ic_round_preview_24)
                ),
                pageState = pageState
            )

            Spacer(modifier = Modifier.height(32.dp))

            when(pageState){
                //Identity Form
                0 -> {
                    CommonInformationForm(
                        title = titleTextFieldValue,
                        onTitleChange = {titleTextFieldValue = it},
                        artist = artistTextFieldValue,
                        onArtistChange = {artistTextFieldValue = it},
                        creationDate = selectedDateValue,
                        onCreationDateChange = {selectedDateValue = it},
                        FineArtCategoryId = selectedFineArtCategoryOptionId,
                        onFineArtCategoryChange = {selectedArtworkCategoryOptionId = it},
                        artworkCategoryId = selectedArtworkCategoryOptionId,
                        onArtworkCategoryChange = { selectedArtworkCategoryOptionId = it } ,
                        scaredStatusId = selectedScaredStatusOptionId,
                        onScaredStatusChange = {selectedScaredStatusOptionId = it},
                        value = artworkValueTextFieldValue,
                        onValueChange = {artworkValueTextFieldValue = it},
                        description = artworkDescriptionTextFieldValue,
                        onDescriptionChange = { artworkDescriptionTextFieldValue = it },
                        function = artworkFunctionTextFieldValue,
                        onFunctionChange = {artworkFunctionTextFieldValue = it},
                        onNextClick = { pageState++ }
                    )
                }
                //Dimension Form
                1 -> {
                    DimensionForm(
                        height = heightTextFieldValue,
                        onHeightChange = {heightTextFieldValue = it},
                        width = widthTextFieldValue,
                        onWidthChange = {widthTextFieldValue = it},
                        long = longTextFieldValue,
                        onLongChange = {longTextFieldValue = it},
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }

                //Video Form
                2 -> {
                    VideoForm(
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }

                //Location Form
                3 -> {
                    LocationForm(
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }
                4 -> {
                    ArtworkStatusForm(
                        artworkStatusId = selectedArtworkStatusOptionId,
                        onArtworkStatusChange = {selectedArtworkStatusOptionId = it},
                        link = linkTextFieldValue,
                        onLinkChange = {linkTextFieldValue = it},
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }
                5 -> {
                    DocumentForm(
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }
                6 -> {
                    DetailArtworkForm(
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }
                7 -> {
                    ArtworkOwnershipHistoryForm(
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }
                8 -> {
                    ArtworkAchievementForm(
                        onPreviousClick = { pageState-- },
                        onNextClick = { pageState++ }
                    )
                }
                9 -> {

                }
            }
        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.submission_artwork_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@Composable
private fun CommonInformationForm(
    title: String,
    onTitleChange : (String) -> Unit,
    artist: String,
    onArtistChange : (String) -> Unit,
    creationDate: String,
    onCreationDateChange : (String) -> Unit,
    FineArtCategoryId: Int,
    onFineArtCategoryChange : (Int) -> Unit,
    artworkCategoryId: Int,
    onArtworkCategoryChange : (Int) -> Unit,
    scaredStatusId: Int,
    onScaredStatusChange : (Int) -> Unit,
    value: String,
    onValueChange : (String) -> Unit,
    description: String,
    onDescriptionChange : (String) -> Unit,
    function: String,
    onFunctionChange : (String) -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current

    // Artwork Title TextField State
    var isTitleTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var titleTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val titleTextFieldFocusRequester = FocusRequester()

    // Artist TextField State
    var isArtistTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var artistTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val artistTextFieldFocusRequester = FocusRequester()

    // Artwork Creation Date Picker State
    var isCreationDatePickerError: Boolean by rememberSaveable { mutableStateOf(false)}
    var creationDatePickerErrorMessage: String by rememberSaveable { mutableStateOf("")}
    val creationDatePickerFocusRequester = FocusRequester()

    // Fine Art Category Selector State
    var isFineArtSelectorError: Boolean by rememberSaveable { mutableStateOf(false) }
    var fineArtSelectorErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val fineArtSelectorFocusRequester = FocusRequester()

    // Artwork Category Selector State
    var isArtworkSelectorError: Boolean by rememberSaveable { mutableStateOf(false) }
    var artworkSelectorErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val artworkSelectorFocusRequester = FocusRequester()

    // Scared Status Selector State
    var isScaredStatusSelectorError: Boolean by rememberSaveable { mutableStateOf(false) }
    var scaredStatusSelectorErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val scaredStatusSelectorFocusRequester = FocusRequester()

    // Artwork Value TextField State
    var isArtworkValueTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var artworkValueTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val artworkValueTextFieldFocusRequester = FocusRequester()

    // Artwork Description TextField State
    var isArtworkDescriptionTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var artworkDescriptionTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val artworkDescriptionTextFieldFocusRequester = FocusRequester()

    // Artwork Function TextField State
    var isArtworkFunctionTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var artworkFunctionTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val artworkFunctionTextFieldFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_common_information_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            textAlign = TextAlign.Justify,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_common_information_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Artwork Image Picker
        ImagePicker(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_add_photo_alternate_24),
            text = stringResource(id = R.string.label_input_artwork_image),
            imageUrl = "",
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))
        // Artwork Title Text Field
        ShortTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_title_24),
            label = stringResource(id = R.string.label_title_artwork),
            text = title,
            isInputError = isTitleTextFieldError,
            errorMessage = titleTextFieldErrorMessage,
            focusRequester = titleTextFieldFocusRequester,
            onInputChange = {
                onTitleChange(it)
                if (Validation.isEmpty(it)) {
                    isTitleTextFieldError = true
                    titleTextFieldErrorMessage = context.getString(R.string.error_empty_username)
                } else {
                    isTitleTextFieldError = false
                }
            }
        )

        // Artist Name Text Field
        ShortTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_person_24),
            label = stringResource(id = R.string.label_artist_name),
            text = artist,
            isInputError = isArtistTextFieldError,
            errorMessage = artistTextFieldErrorMessage,
            focusRequester = artistTextFieldFocusRequester,
            onInputChange = {
                onArtistChange(it)
                if (Validation.isEmpty(it)) {
                    isArtistTextFieldError = true
                    artistTextFieldErrorMessage = context.getString(R.string.error_empty_username)
                } else {
                    isArtistTextFieldError = false
                }
            }
        )

        SimpleDatePicker(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.label_creation_date),
            selectedDate = creationDate,
            isInputError = isCreationDatePickerError,
            errorMessage = creationDatePickerErrorMessage,
            focusRequester = creationDatePickerFocusRequester,
            onDateChange = {onCreationDateChange(it)}
        )

        SimpleOptionSelector(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_category_24),
            label = stringResource(id = R.string.label_fine_art_category),
            selectedOptionId = FineArtCategoryId,
            options = listOf(
                OptionItem(id = 1, option = "Lukisan"),
                OptionItem(id = 2, option = "Ukiran"),
                OptionItem(id = 3, option = "Rerajahan"),
                OptionItem(id = 4, option = "Textil"),
            ),
            isInputError = isFineArtSelectorError,
            errorMessage = fineArtSelectorErrorMessage,
            focusRequester = fineArtSelectorFocusRequester,
            onOptionSelected = {onFineArtCategoryChange(it)}
        )

        SimpleOptionSelector(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_palette_24),
            label = stringResource(id = R.string.label_artwork_category),
            selectedOptionId = artworkCategoryId,
            options = listOf(
                OptionItem(id = 1, option = "Kawisesan"),
                OptionItem(id = 2, option = "Kakebalan"),
            ),
            isInputError = isArtworkSelectorError,
            errorMessage = artworkSelectorErrorMessage,
            focusRequester = artworkSelectorFocusRequester,
            onOptionSelected = {onArtworkCategoryChange(it)}
        )

        SimpleOptionSelector(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_warning_24),
            label = stringResource(id = R.string.label_scared_status),
            selectedOptionId = scaredStatusId,
            options = listOf(
                OptionItem(id = 1, option = "Sakral"),
                OptionItem(id = 2, option = "Tidak Sakral"),
            ),
            isInputError = isScaredStatusSelectorError,
            errorMessage = scaredStatusSelectorErrorMessage,
            focusRequester = scaredStatusSelectorFocusRequester,
            onOptionSelected = {onScaredStatusChange(it)}
        )

        // Artwork Value Text Field
        NumberTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_attach_money_24),
            label = stringResource(id = R.string.label_value),
            text = value,
            isInputError = isArtworkValueTextFieldError,
            errorMessage = artworkValueTextFieldErrorMessage,
            focusRequester = artworkValueTextFieldFocusRequester,
            onInputChange = {
                onValueChange(it)
            }
        )

        // Artwork Description Text Field
        LongTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.label_input_artwork_description),
            text = description,
            isInputError = isArtworkDescriptionTextFieldError,
            errorMessage = artworkDescriptionTextFieldErrorMessage,
            focusRequester = artworkDescriptionTextFieldFocusRequester,
            onInputChange = {
                onDescriptionChange(it)
            }
        )

        // Artwork Function Text Field
        LongTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.label_input_artwork_function),
            text = function,
            isInputError = isArtworkFunctionTextFieldError,
            errorMessage = artworkFunctionTextFieldErrorMessage,
            focusRequester = artworkFunctionTextFieldFocusRequester,
            onInputChange = {
                onFunctionChange(it)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Composable
private fun DimensionForm(
    height: String,
    onHeightChange : (String) -> Unit,
    width: String,
    onWidthChange : (String) -> Unit,
    long: String,
    onLongChange : (String) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
){
    //Context
    val context = LocalContext.current

    // Artwork Height TextField State
    var isHeightTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var heightTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val heightTextFieldFocusRequester = FocusRequester()

    // Artwork Width TextField State
    var isWidthTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var widthTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val widthTextFieldFocusRequester = FocusRequester()

    // Artwork Long TextField State
    var isLongTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var longTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val longTextFieldFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Page Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_dimension_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            textAlign = TextAlign.Justify,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_dimension_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Height Text Field
        NumberTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_width_normal_24),
            label = stringResource(id = R.string.label_input_artwork_dimension_height),
            text = height,
            isInputError = isHeightTextFieldError,
            errorMessage = heightTextFieldErrorMessage,
            focusRequester = heightTextFieldFocusRequester,
            onInputChange = {
                onHeightChange(it)
            }
        )

        // Width Text Field
        NumberTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_width_wide_24),
            label = stringResource(id = R.string.label_input_artwork_dimension_width),
            text = width,
            isInputError = isWidthTextFieldError,
            errorMessage = widthTextFieldErrorMessage,
            focusRequester = widthTextFieldFocusRequester,
            onInputChange = {
                onWidthChange(it)
            }
        )

        // Long Text Field
        NumberTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_width_full_24),
            label = stringResource(id = R.string.label_input_artwork_dimension_long),
            text = long,
            isInputError = isLongTextFieldError,
            errorMessage = longTextFieldErrorMessage,
            focusRequester = longTextFieldFocusRequester,
            onInputChange = {
                onLongChange(it)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Composable
private fun VideoForm(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_video_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            textAlign = TextAlign.Justify,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_video_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Artwork Image Picker
        VideoPicker(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_video_call_24),
            text = stringResource(id = R.string.label_input_artwork_video),
            imageUrl = "",
        ) {

        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Composable
private fun LocationForm(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_location_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_location_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Location Picker
        LocationPicker(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_add_location_24),
            text = stringResource(id = R.string.label_input_artwork_location),
            imageUrl = "",
        ) {

        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ArtworkStatusForm(
    artworkStatusId: Int,
    onArtworkStatusChange : (Int) -> Unit,
    link: String,
    onLinkChange : (String) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current

    // Artwork Status Selector State
    var isArtworkStatusSelectorError: Boolean by rememberSaveable { mutableStateOf(false) }
    var artworkStatusSelectorErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val artworkStatusSelectorFocusRequester = FocusRequester()

    // Artwork Link TextField State
    var isLinkTextFieldError: Boolean by rememberSaveable { mutableStateOf(false) }
    var linkTextFieldErrorMessage: String by rememberSaveable { mutableStateOf("") }
    val linkTextFieldFocusRequester = FocusRequester()


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_status_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            textAlign = TextAlign.Justify,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_status_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Artwork Status Selector
        SimpleOptionSelector(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_gavel_24),
            label = stringResource(id = R.string.label_input_artwork_status),
            selectedOptionId = artworkStatusId,
            options = listOf(
                OptionItem(id = 1, option = "Koleksi Pribadi"),
                OptionItem(id = 2, option = "Dijual"),
                OptionItem(id = 3, option = "Dilelang"),
            ),
            isInputError = isArtworkStatusSelectorError,
            errorMessage = artworkStatusSelectorErrorMessage,
            focusRequester = artworkStatusSelectorFocusRequester,
            onOptionSelected = {onArtworkStatusChange(it)}
        )

        // Artwork Link Text Field
        ShortTextField(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_insert_link_24),
            label = stringResource(id = R.string.label_input_artwork_auction_link),
            text = link,
            isInputError = isLinkTextFieldError,
            errorMessage = linkTextFieldErrorMessage,
            focusRequester = linkTextFieldFocusRequester,
            onInputChange = {
                onLinkChange(it)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Composable
private fun DocumentForm(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_location_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_location_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Document Owner Picker
        DocumentPicker(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_document_scanner_24),
            text = stringResource(id = R.string.label_input_artwork_owner_document),
            imageUrl = "",
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))

        //Document Owner Picker
        DocumentPicker(
            modifier = Modifier
                .fillMaxWidth(),
            icon = painterResource(id = R.drawable.ic_round_document_scanner_24),
            text = stringResource(id = R.string.label_input_artwork_haki_document),
            imageUrl = "",
        ) {

        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun DetailArtworkForm(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_detail_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_detail_description)
        )

        //Detail Artwork List

        Spacer(modifier = Modifier.height(16.dp))

        //Detail ArtworkPicker
        DetailArtworkPicker(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.label_input_artwork_detail),
        ) {

        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ArtworkAchievementForm(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_achievement_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_achievement_description)
        )

        //Detail Artwork List


        Spacer(modifier = Modifier.height(16.dp))

        //Detail ArtworkPicker
        DetailArtworkPicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = stringResource(id = R.string.label_input_artwork_achievement),
        ) {

        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun ArtworkOwnershipHistoryForm(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
){
    //Context
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Artwork Title
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.h2,
            textAlign = TextAlign.Left,
            maxLines = 1,
            text = stringResource(id = R.string.submission_artwork_ownership_title)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Page Description Text
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1,
            maxLines = 5,
            text = stringResource(id = R.string.submission_artwork_ownership_description)
        )

        //Detail Artwork List


        Spacer(modifier = Modifier.height(16.dp))

        //Detail ArtworkPicker
        DetailArtworkPicker(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.label_input_artwork_ownership_history),
        ) {

        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            // Previous Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_previous),
                isVisible = true,
                isEnable = true
            ){
                onPreviousClick()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next Button
            PositiveTextButtonFlex(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                text = stringResource(id = R.string.label_next),
                isVisible = true,
                isEnable = true
            ){
                onNextClick()
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}


@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkSubmissionScreen(navController = rememberNavController())
    }
}