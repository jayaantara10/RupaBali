package id.jayaantara.rupabali.view.ui.main.home

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.utils.gridItems
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.MainRoute
import id.jayaantara.rupabali.view.ui.main.toArtworkScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navController: NavController
){

    // Search State
    val searchBarHint = stringResource(id = R.string.label_home_search_bar)
    var searchBarValue: String by rememberSaveable { mutableStateOf("") }

    // Welcome State
    val welcomeLabel = stringResource(id = R.string.welcome_text)
    var currentUserNameValue: String by rememberSaveable { mutableStateOf("John Doe") }

    // Exploration Title
    val explorationTitle = stringResource(id = R.string.exploration_title)

    // Popular Artworks Title
    val popularArtworksTitle = stringResource(id = R.string.popular_artworks_title)

    // Artworks Title
    val artworksTitle = stringResource(id = R.string.artwork_title)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 112.dp)
    ){
        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(bottom = 16.dp)
            ) {

                //Context
                val context = LocalContext.current

                //References
                val (
                    searchBar,
                    homeImageBackground,
                    lightReducerOverlay,
                    gradientOverlay,
                    welcomeText,
                    currentUserName,
                    newsSlide,
                    explorationTitleText,
                    explorationMenu,
                    popularArtworksTitleText,
                    popularArtworksSlide,
                ) = createRefs()

                // Home Image Background
                Image(
                    modifier = Modifier
                        .constrainAs(homeImageBackground){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .height(280.dp),
                    painter = painterResource(id = R.drawable.lukisan_3),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )


                //LightReducer Overlay
                Box(
                    modifier = Modifier
                        .constrainAs(lightReducerOverlay) {
                            top.linkTo(homeImageBackground.top)
                            bottom.linkTo(homeImageBackground.bottom)
                            start.linkTo(homeImageBackground.start)
                            end.linkTo(homeImageBackground.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .background(
                            color = Color.Black.copy(
                                alpha = 0.4f
                            )
                        ),
                )

                //Gradient Overlay
                Box(
                    modifier = Modifier
                        .constrainAs(gradientOverlay) {
                            top.linkTo(homeImageBackground.top)
                            bottom.linkTo(homeImageBackground.bottom)
                            start.linkTo(homeImageBackground.start)
                            end.linkTo(homeImageBackground.end)
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


                //Search Bar
                SearchBar(
                    modifier = Modifier
                        .constrainAs(searchBar) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    hint = searchBarHint,
                    text = searchBarValue,
                    onInputChange = {
                        searchBarValue = it
                    }
                ) {
                    searchBarValue = ""
                }

                // Welcome Text
                Text(
                    modifier = Modifier
                        .constrainAs(welcomeText) {
                            top.linkTo(searchBar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    color = Color.White,
                    maxLines = 1,
                    text = welcomeLabel
                )

                // Current User Name
                Text(
                    modifier = Modifier
                        .constrainAs(currentUserName) {
                            top.linkTo(welcomeText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    color = Color.White,
                    maxLines = 1,
                    text = currentUserNameValue
                )

                //News Slide
                NewsSlider(
                    modifier = Modifier
                        .constrainAs(newsSlide) {
                            top.linkTo(currentUserName.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp),
                    newsItems = newsItems,
                    navController = navController
                )

                // Exploration Title Text
                Text(
                    modifier = Modifier
                        .constrainAs(explorationTitleText) {
                            top.linkTo(newsSlide.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    maxLines = 1,
                    text = explorationTitle
                )

                //Exploration Menu
                ExplorationMenu(
                    modifier = Modifier
                        .constrainAs(explorationMenu) {
                            top.linkTo(explorationTitleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(vertical = 16.dp),
                    navController = navController
                )

                // Popular Artworks Title Text
                Text(
                    modifier = Modifier
                        .constrainAs(popularArtworksTitleText) {
                            top.linkTo(explorationMenu.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    maxLines = 1,
                    text = popularArtworksTitle
                )

                //Popular Artworks Slide
                PopularArtworksSlider(
                    modifier = Modifier
                        .constrainAs(popularArtworksSlide) {
                            top.linkTo(popularArtworksTitleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp),
                    popularArtworkItems = popularArtworkItems,
                    navController = navController
                )
            }
        }

        stickyHeader {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(top = 18.dp)
            ) {
                // Artworks Title Text
                Text(
                    modifier = Modifier
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    maxLines = 1,
                    text = artworksTitle
                )

                CategoryList(
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    categoryItems = fineArtCategoryItems,
                    navController = navController
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
                toArtworkScreen(navController = navController)
            }
        }
    }
}

@Composable
private fun CategoryList(
    modifier: Modifier,
    categoryItems: List<FineArtCategoryItem>,
    navController: NavController
){
    val allCategoryItem = FineArtCategoryItem (
        category = stringResource(id = R.string.label_all_category),
        imageUrl = "https://www.dictio.id/uploads/db3342/original/3X/7/f/7f5e20719e3d85982c2c4793173e56468bd3977d.jpg"
    )

    var selectedCategory: String by rememberSaveable { mutableStateOf(allCategoryItem.category) }

    val context = LocalContext.current
    Box(
        modifier = modifier
    ){
         LazyRow(
             contentPadding = PaddingValues( horizontal = 28.dp)
         ){
             item {
                 CategoryCardSmall(
                     modifier = Modifier
                         .padding(4.dp),
                     item = allCategoryItem,
                     isSelected = selectedCategory.equals(allCategoryItem.category)
                 ) {
                     selectedCategory = allCategoryItem.category
                     Log.d("Test", selectedCategory)
                 }
             }

              items( items = categoryItems){ categoryItem ->
                  CategoryCardSmall(
                      modifier = Modifier
                          .padding(4.dp),
                      item = categoryItem,
                      isSelected = selectedCategory.equals(categoryItem.category)
                  ) {
                      selectedCategory = categoryItem.category
                      Log.d("Test", selectedCategory)
                  }
              }
         }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
private fun ArtworkList(
    modifier: Modifier,
    artworkItems: List<ArtworkItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
        ){
            items(items = artworkItems) { artworkItem ->
                ArtworkCard(
                    modifier = Modifier,
                    item = artworkItem)
                {

                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PopularArtworksSlider(
    modifier: Modifier,
    popularArtworkItems: List<PopularArtworkItem>,
    navController: NavController
){
    val pagerState = rememberPagerState(
        pageCount = popularArtworkItems.size,
        initialPage = 1
    )

    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Box(
        modifier = modifier
    ) {
        Column {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                state = pagerState
            ) { page ->
                PopularArtworkCardSlider(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    item = popularArtworkItems[page]
                ) {

                }
            }
        }
    }
}

@Composable
private fun ExplorationMenu(
    modifier: Modifier,
    navController: NavController
){

    val scrollState = rememberScrollState()

    val coverEducationMenu = painterResource(id = R.drawable.cover_menu_edukasi)
    val educationMenuTitle = stringResource(id = R.string.education_title)

    val coverEventMenu = painterResource(R.drawable.cover_menu_event)
    val eventMenuTitle = stringResource(id = R.string.event_title)

    val coverNewsMenu = painterResource(R.drawable.cover_menu_berita)
    val newsMenuTitle = stringResource(id = R.string.news_title)

    val coverUserMenu = painterResource(R.drawable.cover_menu_user)
    val userMenuTitle = stringResource(id = R.string.user_title)

    Box(
        modifier = modifier
    ) {
        Row(modifier = Modifier
            .horizontalScroll(state = scrollState)
            .padding(horizontal = 32.dp)
        ) {
            LargeCardMenu(
                modifier = Modifier
                    .padding(end = 8.dp),
                cover = coverEducationMenu,
                title = educationMenuTitle
            ) {
                toEducationScreen(navController = navController)
            }

            Column(modifier = Modifier
                .padding(end = 8.dp)
            ) {
                SmallCardMenu(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    cover = coverEventMenu,
                    title = eventMenuTitle
                ) {
                    toEventListScreen(navController = navController)
                }

                SmallCardMenu(
                    modifier = Modifier,
                    cover = coverNewsMenu,
                    title = newsMenuTitle
                ) {
                    toNewsListScreen(navController = navController)
                }
            }

            Column(modifier = Modifier
                .padding(end = 8.dp)
            ) {
                SmallCardMenu(
                    modifier = Modifier,
                    cover = coverUserMenu,
                    title = userMenuTitle
                ) {
                    toUserListScreen(navController = navController)
                }
            }
        }
    }
}

private fun toNewsListScreen(navController: NavController) {
    navController.navigate(MainRoute.NEWS_LIST)
}

private fun toEducationScreen(navController: NavController) {
    navController.navigate(MainRoute.EDUCATION)
}

private fun toEventListScreen(navController: NavController) {
    navController.navigate(MainRoute.EVENT_LIST)
}

private fun toUserListScreen(navController: NavController) {
    navController.navigate(MainRoute.USER_LIST)
}

@ExperimentalPagerApi
@Composable
private fun NewsSlider(
    modifier: Modifier,
    newsItems: List<NewsItem>,
    navController: NavController
){
    val pagerState = rememberPagerState(
        pageCount = newsItems.size,
        initialPage = 2
    )

    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Box(
        modifier = modifier
    ) {
        Column {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                state = pagerState
            ) { page ->
                NewsCardSlider(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f,1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    item = newsItems[page]
                ) {

                }
            }

            // Pager Indicator
            PagerIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                items = newsItems,
                currentPage = pagerState.currentPage
            )
        }
    }

}

@ExperimentalPagerApi
@Preview
@Composable
private fun HomeScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        HomeScreen(navController = rememberNavController())
    }
}
