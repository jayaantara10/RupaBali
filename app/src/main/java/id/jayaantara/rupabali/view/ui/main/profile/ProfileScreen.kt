package id.jayaantara.rupabali.view.ui.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.utils.gridItems
import id.jayaantara.rupabali.utils.isScrolledToTheEnd
import id.jayaantara.rupabali.view.components.ArtworkCard
import id.jayaantara.rupabali.view.components.SimpleTopAppBar
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.home.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    navController: NavController
){

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(bottom = 112.dp),
        state = lazyListState
    ){
        item{
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
                    topAppbar,
                    backgroundPicture,
                    lightReducerOverlay,
                    gradientOverlay,
                    profilePicture,
                    nicknameText,
                    roleText,
                    artworkAndLikeCounterText,
                    descriptionText,
                ) = createRefs()


                // Home Image Background
                Image(
                    modifier = Modifier
                        .constrainAs(backgroundPicture) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .height(296.dp),
                    painter = rememberAsyncImagePainter(dummyProfile.backgroudPicture),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                //LightReducer Overlay
                Box(
                    modifier = Modifier
                        .constrainAs(lightReducerOverlay) {
                            top.linkTo(backgroundPicture.top)
                            bottom.linkTo(backgroundPicture.bottom)
                            start.linkTo(backgroundPicture.start)
                            end.linkTo(backgroundPicture.end)
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
                            top.linkTo(backgroundPicture.top)
                            bottom.linkTo(backgroundPicture.bottom)
                            start.linkTo(backgroundPicture.start)
                            end.linkTo(backgroundPicture.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                0f to MaterialTheme.colors.background,
                                0.2f to Color.Transparent,
                                0.8f to Color.Transparent,
                                1.0f to MaterialTheme.colors.background,
                            )
                        )
                )

                // Profile Picture
                Image(
                    modifier = Modifier
                        .constrainAs(profilePicture) {
                            top.linkTo(backgroundPicture.bottom)
                            bottom.linkTo(backgroundPicture.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .size(144.dp)
                        .clip(shape = CircleShape),
                    painter = rememberAsyncImagePainter(dummyProfile.profilePicture),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                // Nickname Text
                Text(
                    modifier = Modifier
                        .constrainAs(nicknameText) {
                            top.linkTo(profilePicture.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    text = dummyProfile.nickname
                )

                // Role Text
                Text(
                    modifier = Modifier
                        .constrainAs(roleText) {
                            top.linkTo(nicknameText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = dummyProfile.role
                )

                // Arwork and Like Counter
                Row(
                    modifier = Modifier
                        .constrainAs(artworkAndLikeCounterText) {
                            top.linkTo(roleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Column (
                        modifier = Modifier
                            .width(144.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = Typography.h1,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = dummyProfile.artworkCount
                        )

                        Text(
                            style = Typography.h3,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = stringResource(id = R.string.artwork_label)
                        )

                    }

                    Column (
                        modifier = Modifier
                            .width(144.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = Typography.h1,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = dummyProfile.likeCount
                        )

                        Text(
                            style = Typography.h3,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = stringResource(id = R.string.like_label)
                        )

                    }
                }

                // Description Text
                Text(
                    modifier = Modifier
                        .constrainAs(descriptionText) {
                            top.linkTo(artworkAndLikeCounterText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 24.dp, start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Justify,
                    text = dummyProfile.description
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
        title = stringResource(id = R.string.profile_title),
        isButtonOptionVisible = true,
        menuList = stringArrayResource(id = R.array.label_menu_profile),
        onMenuClick = { selectedMenuIndex ->
            when (selectedMenuIndex) {
                0 -> {
                    toArtworkSubmissionListScreen(navController = navController)
                }
                1 -> {
                    toManagementAccountScreen(navController = navController)
                }
                2 -> {
                }
                3 -> {

                }
            }
        }
    )

}

private fun toManagementAccountScreen(navController: NavController){
    navController.navigate(MainRoute.MANAGEMENT_ACCOUNT)
}


@ExperimentalPagerApi
@Preview
@Composable
private fun ProfileScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ProfileScreen(navController = rememberNavController())
    }
}
