package id.jayaantara.rupabali.view.ui.main.artwork

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.utils.Validation
import id.jayaantara.rupabali.utils.gridItems
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.MainRoute
import id.jayaantara.rupabali.view.ui.main.education.ArtworkCategoryScreen
import id.jayaantara.rupabali.view.ui.main.education.navToFineArtDetails
import id.jayaantara.rupabali.view.ui.main.toAchievementDetailScreen
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtworkAchievementDetailScreen(
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
                    .padding(top = 32.dp)
            ) {

                //Context
                val context = LocalContext.current

                //References
                val (
                    titleText,
                    date,
                    imageCard,
                    descriptionText,
                    ertificateButton,
                ) = createRefs()

                // Artwork Title
                Text(
                    modifier = Modifier
                        .constrainAs(titleText) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    textAlign = TextAlign.Left,
                    maxLines = 2,
                    text = dummyAchievementItems[0].title
                )

                // Artist
                Text(
                    modifier = Modifier
                        .constrainAs(date) {
                            top.linkTo(titleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Left,
                    maxLines = 2,
                    text = dummyAchievementItems[0].date
                )

                //Image Card
                ImageCard(
                    modifier = Modifier
                        .constrainAs(imageCard) {
                            top.linkTo(date.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp)
                        .height(200.dp),
                    imageUrl = dummyAchievementItems[0].documentation,
                    onClick = {}
                )

                //Description
                Text(
                    modifier = Modifier
                        .constrainAs(descriptionText) {
                            top.linkTo(imageCard.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Justify,
                    text = dummyAchievementItems[0].description
                )

                //Button Certificate
                PositiveTextButtonFlex(
                    modifier = Modifier
                        .constrainAs(ertificateButton) {
                            top.linkTo(descriptionText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp)
                        .wrapContentHeight(),
                    text = stringResource(id = R.string.label_certificate),
                    isVisible = true,
                    isEnable = true
                ){

                }

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
                    text = stringResource(id = R.string.other_achievement_title)
                )
            }
        }

        items( items = dummyAchievementItems){ item ->
            AchievementCard(
                modifier = Modifier
                    .padding(horizontal = 28.dp),
                item = item)
            {
                toAchievementDetailScreen(navController = navController)
            }
        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.artwork_achievement_detail_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkAchievementDetailScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkAchievementDetailScreen(navController = rememberNavController())
    }
}