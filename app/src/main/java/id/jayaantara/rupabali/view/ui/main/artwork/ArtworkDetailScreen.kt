package id.jayaantara.rupabali.view.ui.main.artwork

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.components.ExpandableDetails
import id.jayaantara.rupabali.view.components.ImageCard
import id.jayaantara.rupabali.view.components.PagerIndicator
import id.jayaantara.rupabali.view.components.SimpleTopAppBar
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.DetailArtworkItem
import id.jayaantara.rupabali.view.ui.data.dummy.dummyDetailArtworkItems
import id.jayaantara.rupabali.view.ui.data.dummy.dummyFineArtCategoryDetails
import id.jayaantara.rupabali.view.ui.data.static.AddDataOnBoarding
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun ArtworkDetailScreen(
    navController: NavController
){

    val lazyListState = rememberLazyListState()
    val isReachStickyHeader = lazyListState.firstVisibleItemIndex >= 1

    val paddingTopStickyHeader: Dp by animateDpAsState(if (isReachStickyHeader) 88.dp else 32.dp)

    val pagerState = rememberPagerState(
        pageCount = dummyDetailArtworkItems.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(bottom = 32.dp),
        state = lazyListState
    ){

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 88.dp)
            ) {
                //Detail Artwork Pager
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth(),
                    state = pagerState
                ) { page ->
                    //Image Card
                    ImageCard(
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .height(296.dp),
                        imageUrl = dummyDetailArtworkItems[page].imageUrl,
                        onClick = {}
                    )
                }

                // Pager Indicator
                PagerIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    items = dummyDetailArtworkItems,
                    currentPage = pagerState.currentPage
                )

            }

        }

        stickyHeader{
            //Description Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(top = paddingTopStickyHeader, bottom = 16.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(
                    style = Typography.h2,
                    maxLines = 1,
                    text = dummyDetailArtworkItems[pagerState.currentPage].title
                )
            }
        }

        item {
            //Description
            Text(
                modifier = Modifier
                    .padding(bottom = 32.dp, start = 32.dp, end = 32.dp),
                style = Typography.body1,
                textAlign = TextAlign.Justify,
                text = dummyDetailArtworkItems[pagerState.currentPage].description
            )

        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.artwork_detail_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}


@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkDetailScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkDetailScreen(navController = rememberNavController())
    }
}