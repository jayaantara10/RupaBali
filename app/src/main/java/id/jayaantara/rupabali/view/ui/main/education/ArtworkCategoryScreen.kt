package id.jayaantara.rupabali.view.ui.main.education

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.utils.gridItems
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.artworkCategoryItems
import id.jayaantara.rupabali.view.ui.data.dummy.artworkItems
import id.jayaantara.rupabali.view.ui.data.dummy.dummyFineArtCategoryDetails
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtworkCategoryScreen(
    navController: NavController
) {

    val lazyListState = rememberLazyListState()
    val isReachStickyHeader = lazyListState.firstVisibleItemIndex >= 1

    val paddingTopStickyHeader: Dp by animateDpAsState(if (isReachStickyHeader) 88.dp else 32.dp)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(top = 56.dp, bottom = 32.dp),
        state = lazyListState
    ){
        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(top = 32.dp, bottom = 16.dp)
            ) {

                //Context
                val context = LocalContext.current

                //References
                val (
                    imageCard,
                    descriptionView,
                    historyView,
                    functionView,
                ) = createRefs()

                //Image Card
                ImageCard(
                    modifier = Modifier
                        .constrainAs(imageCard) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(horizontal = 32.dp)
                        .height(296.dp),
                    imageUrl = dummyFineArtCategoryDetails.imageUrl,
                    onClick = {}
                )

                var isExpandedDescription: Boolean by rememberSaveable { mutableStateOf(true) }
                var isExpandedHistory: Boolean by rememberSaveable { mutableStateOf(false) }
                var isExpandedFunction: Boolean by rememberSaveable { mutableStateOf(false) }

                //Description
                ExpandableDetails(
                    modifier = Modifier
                        .constrainAs(descriptionView) {
                            top.linkTo(imageCard.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    title = stringResource(id = R.string.description_title),
                    textDetails = artworkCategoryItems[0].description,
                    isExpanded = isExpandedDescription,
                    onExpandClick = { isExpandedDescription = !isExpandedDescription }
                )

                //History
                ExpandableDetails(
                    modifier = Modifier
                        .constrainAs(historyView) {
                            top.linkTo(descriptionView.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    title = stringResource(id = R.string.history_title),
                    textDetails = artworkCategoryItems[0].history,
                    isExpanded = isExpandedHistory,
                    onExpandClick = { isExpandedHistory = !isExpandedHistory }
                )

                //Function Title Text
                ExpandableDetails(
                    modifier = Modifier
                        .constrainAs(functionView) {
                            top.linkTo(historyView.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    title = stringResource(id = R.string.function_title),
                    textDetails = artworkCategoryItems[0].function,
                    isExpanded = isExpandedFunction,
                    onExpandClick = { isExpandedFunction = !isExpandedFunction }
                )
            }
        }

        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(top = paddingTopStickyHeader, bottom = 16.dp, start = 32.dp, end = 32.dp)
            ) {
                // Artworks Title Text
                Text(
                    style = Typography.h2,
                    maxLines = 1,
                    text = stringResource(id = R.string.artwork_title)
                )
            }
        }

        gridItems(
            data = artworkItems,
            columnCount = 2,
            modifier = Modifier.padding(horizontal = 28.dp)
        ){ artworkItem ->
            ArtworkCard(
                modifier = Modifier,
                item = artworkItem)
            {

            }
        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = artworkCategoryItems[0].category,
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkCategoryScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkCategoryScreen(navController = rememberNavController())
    }
}