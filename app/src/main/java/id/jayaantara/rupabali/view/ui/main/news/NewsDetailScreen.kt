package id.jayaantara.rupabali.view.ui.main.news

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.artwork.ArtworkAchievementDetailScreen
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsDetailScreen(
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
                    titleText,
                    date,
                    sparator,
                    source,
                    imageCard,
                    descriptionText,
                    realArticleButton,
                ) = createRefs()

                //Title
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
                    text = newsItems[0].title
                )

                // Source
                Text(
                    modifier = Modifier
                        .constrainAs(source) {
                            top.linkTo(titleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(sparator.start)
                        }
                        .padding(start = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Left,
                    maxLines = 1,
                    text = newsItems[0].source
                )

                // Sparator
                Text(
                    modifier = Modifier
                        .constrainAs(sparator) {
                            top.linkTo(titleText.bottom)
                            start.linkTo(source.end)
                        }
                        .padding(start = 4.dp, end = 4.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Left,
                    maxLines = 1,
                    text = "-"
                )

                // Date
                Text(
                    modifier = Modifier
                        .constrainAs(date) {
                            top.linkTo(source.top)
                            start.linkTo(sparator.end)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Left,
                    maxLines = 1,
                    text = newsItems[0].date
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
                    imageUrl = newsItems[0].imageUrl,
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
                    text = newsItems[0].description
                )

                //Button Real Article
                PositiveTextButtonFlex(
                    modifier = Modifier
                        .constrainAs(realArticleButton) {
                            top.linkTo(descriptionText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp)
                        .wrapContentHeight(),
                    text = stringResource(id = R.string.label_real_article),
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
                    text = stringResource(id = R.string.other_news_title)
                )
            }
        }

        items( items = newsItems){ newsItem ->
            NewsCard(
                modifier = Modifier
                    .padding(horizontal = 28.dp),
                item = newsItem)
            {

            }
        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.news_detail_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun NewsDetailScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        NewsDetailScreen(navController = rememberNavController())
    }
}