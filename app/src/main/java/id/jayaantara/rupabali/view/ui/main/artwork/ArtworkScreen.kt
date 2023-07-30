package id.jayaantara.rupabali.view.ui.main.artwork

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import id.jayaantara.rupabali.utils.gridItems
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.artworkCategoryItems
import id.jayaantara.rupabali.view.ui.data.dummy.artworkItems
import id.jayaantara.rupabali.view.ui.data.dummy.userItems
import id.jayaantara.rupabali.view.ui.main.MainRoute
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtworkScreen(
    navController: NavController
){

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
                    artworkTitleText,
                    artworkArtistText,
                    imageCard,
                    sideButton,
                    auctionButton,
                    artworkIdentityView,
                    descriptionView,
                    functionView,
                    ownerTitle,
                    ownerIdentityCard,
                ) = createRefs()

                // Artwork Title
                Text(
                    modifier = Modifier
                        .constrainAs(artworkTitleText) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    text = artworkItems[0].title
                )

                // Artist
                Text(
                    modifier = Modifier
                        .constrainAs(artworkArtistText) {
                            top.linkTo(artworkTitleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    text = artworkItems[0].artist
                )

                //Image Card
                ImageCard(
                    modifier = Modifier
                        .constrainAs(imageCard) {
                            top.linkTo(artworkArtistText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(sideButton.start)
                            bottom.linkTo(sideButton.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 16.dp),
                    imageUrl = artworkItems[0].imageUrl,
                    onClick = {}
                )

                //Side Button
                SideButton(
                    modifier = Modifier
                        .constrainAs(sideButton) {
                            top.linkTo(imageCard.top)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp, end = 32.dp),
                    navController = navController
                )

                // Auction or Selling Button
                PositiveTextButtonFlex(
                    modifier = Modifier
                        .constrainAs(auctionButton) {
                            top.linkTo(imageCard.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                        .wrapContentHeight(),
                    text = stringResource(id = R.string.label_auction)
                ){

                }

                //Identity
                ArtworkIdentityView(
                    modifier = Modifier
                        .constrainAs(artworkIdentityView) {
                            top.linkTo(auctionButton.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    creationDate = artworkItems[0].creation_date,
                    fineArtCategory = artworkItems[0].fine_art_category,
                    artworkCategory = artworkItems[0].artwork_category,
                    scaredStatus = artworkItems[0].scared_status,
                    dimension = artworkItems[0].dimension,
                    owner = artworkItems[0].owner,
                    value = artworkItems[0].value
                )

                var isExpandedDescription: Boolean by rememberSaveable { mutableStateOf(true) }
                var isExpandedFunction: Boolean by rememberSaveable { mutableStateOf(false) }

                //Description
                ExpandableDetails(
                    modifier = Modifier
                        .constrainAs(descriptionView) {
                            top.linkTo(artworkIdentityView.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    title = stringResource(id = R.string.description_title),
                    textDetails = artworkCategoryItems[0].description,
                    isExpanded = isExpandedDescription,
                    onExpandClick = { isExpandedDescription = !isExpandedDescription }
                )

                //Function
                ExpandableDetails(
                    modifier = Modifier
                        .constrainAs(functionView) {
                            top.linkTo(descriptionView.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    title = stringResource(id = R.string.function_title),
                    textDetails = artworkCategoryItems[0].function,
                    isExpanded = isExpandedFunction,
                    onExpandClick = { isExpandedFunction = !isExpandedFunction }
                )

                // Owner Title
                Text(
                    modifier = Modifier
                        .constrainAs(ownerTitle) {
                            top.linkTo(functionView.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    style = Typography.h2,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_owner)
                )

                //Owner
                UserCard(
                    modifier = Modifier
                        .constrainAs(ownerIdentityCard) {
                            top.linkTo(ownerTitle.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 12.dp, start = 28.dp, end = 28.dp),
                    item = userItems[0]
                ) {

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
                    text = stringResource(id = R.string.recent_artwork_title)
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
    ActionTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.artwork_information_title),
        isButtonBackVisible = true,
        isButtonActionVisible = true,
        buttonActionIcon = R.drawable.ic_outline_favorite_border_24,
        onActionPress = {

        },
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@Composable
private fun SideButton(
    modifier: Modifier,
    navController: NavController
){

    Box(
        modifier = modifier
    ) {
        Column {
            // Detail Button
            PositiveIconButton(
                modifier = Modifier
                    .height(56.dp),
                icon = R.drawable.ic_round_perm_media_24,
            ) {
                toArtworkDetailScreen(navController = navController)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Video Button
            PositiveIconButton(
                modifier = Modifier
                    .height(56.dp),
                icon = R.drawable.ic_round_play_circle_24,
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Location Button
            PositiveIconButton(
                modifier = Modifier
                    .height(56.dp),
                icon = R.drawable.ic_round_add_location_24,
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ownership History Button
            PositiveIconButton(
                modifier = Modifier
                    .height(56.dp),
                icon = R.drawable.ic_round_person_24,
            ) {
                toArtworkOwnershipHistoryScreen(navController = navController)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Achievement Button
            PositiveIconButton(
                modifier = Modifier
                    .height(56.dp),
                icon = R.drawable.ic_round_star_24,
            ) {
                toArtworkAchievementListScreen(navController = navController)
            }
        }
    }
}

@Composable
private fun ArtworkIdentityView(
    modifier: Modifier,
    creationDate: String,
    fineArtCategory: String,
    artworkCategory: String,
    scaredStatus: String,
    dimension: String,
    owner: String,
    value: String
){
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                style = Typography.h2,
                maxLines = 1,
                text = stringResource(id = R.string.artwork_identity_title)
            )

            //Creation Date
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_creation_date)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = creationDate
                )
            }

            //Fine Art Category
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_fine_art_category)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = fineArtCategory
                )
            }

            //Artwork Category
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_artwork_category)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = artworkCategory
                )
            }

            //Scared Status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_scared_status)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = scaredStatus
                )
            }

            //Dimension
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_dimension)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = dimension
                )
            }

            //Owner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_owner)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = owner
                )
            }

            //Value
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = stringResource(id = R.string.label_value)
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    text = ":"
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    style = Typography.body1,
                    maxLines = 1,
                    text = value
                )
            }
        }
    }
}

private fun toArtworkDetailScreen(navController: NavController) {
    navController.navigate(MainRoute.ARTWORK_DETAIL)
}

private fun toArtworkOwnershipHistoryScreen(navController: NavController) {
    navController.navigate(MainRoute.ARTWORK_OWNERSHIP_HISTORY)
}

private fun toArtworkAchievementListScreen(navController: NavController) {
    navController.navigate(MainRoute.ARTWORK_ACHIEVEMENT_LIST)
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ArtworkScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkScreen(navController = rememberNavController())
    }
}