package id.jayaantara.rupabali.view.ui.main.artwork

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
import id.jayaantara.rupabali.view.components.FineArtTypeCard
import id.jayaantara.rupabali.view.components.OutlinedSearchBar
import id.jayaantara.rupabali.view.components.OwnershipHistoryCard
import id.jayaantara.rupabali.view.components.SimpleTopAppBar
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.ui.data.dummy.FineArtCategoryItem
import id.jayaantara.rupabali.view.ui.data.dummy.OwnershipHistoryItem
import id.jayaantara.rupabali.view.ui.data.dummy.dummyOwnershipHistoryItems
import id.jayaantara.rupabali.view.ui.data.dummy.fineArtCategoryItems
import id.jayaantara.rupabali.view.ui.main.education.navToFineArtDetails
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ArtworkOwnershipHistoryScreen(
    navController: NavController
){

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        // References
        val (
            fineArtTypeList,
        ) = createRefs()

        //Fine Art Type List
        ArtworkOwnershipHistoryList(
            modifier = Modifier
                .constrainAs(fineArtTypeList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            items = dummyOwnershipHistoryItems,
            navController = navController
        )
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.artwork_ownership_history_title),
        isButtonBackVisible= true,
        onBackPress= {
            toBackScreen(navController = navController)
        }
    )
}


@Composable
private fun ArtworkOwnershipHistoryList(
    modifier: Modifier,
    items: List<OwnershipHistoryItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues(top = 88.dp, start = 28.dp, end = 28.dp, bottom = 36.dp)
        ){
            items( items = items){ item ->
                OwnershipHistoryCard(
                    modifier = Modifier,
                    item = item)
                {
                    navToFineArtDetails(navController = navController)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkOwnershipHistoryScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkOwnershipHistoryScreen(navController = rememberNavController())
    }
}