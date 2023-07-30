package id.jayaantara.rupabali.view.ui.main.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import id.jayaantara.rupabali.view.ui.main.toNewsDetailScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun NewsListScreen(
    navController: NavController
){
    // Search State
    val searchBarHint = stringResource(id = R.string.label_news_search_bar)
    var searchBarValue: String by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 88.dp)
    ) {


        // References
        val (
            searchBar,
            fineArtTypeList,
        ) = createRefs()

        //Search Bar
        OutlinedSearchBar(
            modifier = Modifier
                .constrainAs(searchBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            hint = searchBarHint,
            text = searchBarValue,
            onInputChange = {
                searchBarValue = it
            }
        ) {
            searchBarValue = ""
        }

        //Fine Art Type List
        NewsList(
            modifier = Modifier
                .constrainAs(fineArtTypeList) {
                    top.linkTo(searchBar.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            newsItems = newsItems,
            navController = navController
        )
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.news_title),
        isButtonBackVisible= true,
        onBackPress= {
            toBackScreen(navController = navController)
        }
    )
}


@Composable
private fun NewsList(
    modifier: Modifier,
    newsItems: List<NewsItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues( start = 28.dp, end = 28.dp, bottom = 36.dp)
        ){
            items( items = newsItems){ newsItem ->
                NewsCard(
                    modifier = Modifier,
                    item = newsItem)
                {
                    toNewsDetailScreen(navController = navController)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun NewsListScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        NewsListScreen(navController = rememberNavController())
    }
}