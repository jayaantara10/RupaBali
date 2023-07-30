package id.jayaantara.rupabali.view.ui.main.submission

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
import id.jayaantara.rupabali.view.ui.data.dummy.ArtworkItem
import id.jayaantara.rupabali.view.ui.data.dummy.artworkItems
import id.jayaantara.rupabali.view.ui.main.toArtworkSubmissionScreen
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ArtworkSubmissionListScreen(
    navController: NavController
){
    // Search State
    val searchBarHint = stringResource(id = R.string.label_submission_artwork_search_bar)
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
            artworkVerificatioList,
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

        //User List
        ArtworkSubmissionList(
            modifier = Modifier
                .constrainAs(artworkVerificatioList) {
                    top.linkTo(searchBar.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            items = artworkItems,
            navController = navController
        )
    }

    //TopAppbar
    ActionTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.submission_artwork_title),
        isButtonBackVisible= true,
        isButtonActionVisible = true,
        buttonActionIcon = R.drawable.ic_round_add_box_24,
        onActionPress = {
            toArtworkSubmissionScreen(navController = navController)
        },
        onBackPress= {
            toBackScreen(navController = navController)
        }
    )
}

@Composable
private fun ArtworkSubmissionList(
    modifier: Modifier,
    items: List<ArtworkItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues( start = 28.dp, end = 28.dp, bottom = 36.dp)
        ){
            items( items = items){ item ->
                ArtworkSubmissionCard(
                    modifier = Modifier,
                    item = item)
                {

                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkSubmissionListScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkSubmissionListScreen(navController = rememberNavController())
    }
}