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
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.toAchievementDetailScreen
import id.jayaantara.rupabali.view.ui.main.toBackScreen

@Composable
fun ArtworkAchievementListScreen(
    navController: NavController
){

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        // References
        val (
            topAppbar,
            fineArtTypeList,
        ) = createRefs()

        //TopAppbar
        SimpleTopAppBar(
            modifier = Modifier
                .constrainAs(topAppbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentHeight(),
            title = stringResource(id = R.string.artwork_achievement_title),
            isButtonBackVisible= true,
            onBackPress= {
                toBackScreen(navController = navController)
            }
        )

        //Fine Art Type List
        ArtworkAchievementList(
            modifier = Modifier
                .constrainAs(fineArtTypeList) {
                    top.linkTo(topAppbar.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            items = dummyAchievementItems,
            navController = navController
        )
    }
}


@Composable
private fun ArtworkAchievementList(
    modifier: Modifier,
    items: List<AchievementItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues( top = 32.dp, start = 28.dp, end = 28.dp, bottom = 36.dp)
        ){
            items( items = items){ item ->
                AchievementCard(
                    modifier = Modifier,
                    item = item)
                {
                    toAchievementDetailScreen(navController = navController)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkAchievementListScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkAchievementListScreen(navController = rememberNavController())
    }
}