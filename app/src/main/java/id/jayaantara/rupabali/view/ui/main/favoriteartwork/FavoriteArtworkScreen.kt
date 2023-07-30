package id.jayaantara.rupabali.view.ui.main

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import id.jayaantara.rupabali.view.ui.data.dummy.ArtworkItem
import id.jayaantara.rupabali.view.ui.data.dummy.FineArtCategoryItem
import id.jayaantara.rupabali.view.ui.data.dummy.artworkItems
import id.jayaantara.rupabali.view.ui.data.dummy.fineArtCategoryItems
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun FavoriteArtworkScreen(
    navController: NavController
){

    // Search State
    val searchBarHint = stringResource(id = R.string.label_favorite_artwork_search_bar)
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
            categoryList,
            artworkList,
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

        //Category List
        CategoryList(
            modifier = Modifier
                .constrainAs(categoryList) {
                    top.linkTo(searchBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            categoryItems = fineArtCategoryItems,
            navController = navController
        )

        //Artwork List
        ArtworkList(
            modifier = Modifier
                .constrainAs(artworkList) {
                    top.linkTo(categoryList.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            artworkItems = artworkItems,
            navController = navController
        )
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.favorite_artwork_title),
    )
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

@OptIn(ExperimentalFoundationApi::class)
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
            contentPadding = PaddingValues( start = 28.dp, end = 28.dp, bottom = 112.dp)
        ){
            items( items = artworkItems){ artworkItem ->
                ArtworkCard(
                    modifier = Modifier,
                    item = artworkItem)
                {

                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun FavoriteArtworkScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        FavoriteArtworkScreen(navController = rememberNavController())
    }
}