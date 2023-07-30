package id.jayaantara.rupabali.view.ui.main.event

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
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.artwork.ArtworkAchievementDetailScreen
import id.jayaantara.rupabali.view.ui.main.news.NewsDetailScreen
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventDetailScreen(
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
                    organizer,
                    dateRange,
                    imageCard,
                    actionButtonView,
                    descriptionText,
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
                    text = eventItems[0].title
                )

                // Organizer
                Text(
                    modifier = Modifier
                        .constrainAs(organizer) {
                            top.linkTo(titleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    maxLines = 1,
                    text = eventItems[0].organizer
                )

                // Date
                Text(
                    modifier = Modifier
                        .constrainAs(dateRange) {
                            top.linkTo(organizer.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Left,
                    maxLines = 1,
                    text = eventItems[0].date
                )

                //Image Card
                ImageCard(
                    modifier = Modifier
                        .constrainAs(imageCard) {
                            top.linkTo(dateRange.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp)
                        .height(200.dp),
                    imageUrl = eventItems[0].imageUrl,
                    onClick = {}
                )

                //Action Button
                ActionButtonView(
                    modifier = Modifier
                        .constrainAs(actionButtonView) {
                            top.linkTo(imageCard.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    navController = navController
                )

                //Description
                Text(
                    modifier = Modifier
                        .constrainAs(descriptionText) {
                            top.linkTo(actionButtonView.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                    style = Typography.body1,
                    textAlign = TextAlign.Justify,
                    text = eventItems[0].description
                )

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

        items( items = eventItems){ eventItem ->
            EventCard(
                modifier = Modifier
                    .padding(horizontal = 28.dp),
                item = eventItem)
            {

            }
        }
    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.event_detail_title),
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        }
    )
}

@Composable
private fun ActionButtonView(
    modifier: Modifier,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                PositiveIconButtonFlex(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    icon = R.drawable.ic_round_location_on_24,
                    isVisible = true,
                    isEnable = true
                ){

                }

                Spacer(modifier = Modifier.width(16.dp))

                //Button Email
                PositiveIconButtonFlex(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    icon = R.drawable.ic_round_email_24,
                    isVisible = true,
                    isEnable = true
                ){

                }

                Spacer(modifier = Modifier.width(16.dp))

                //Button Email
                PositiveIconButtonFlex(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    icon = R.drawable.ic_round_phone_24,
                    isVisible = true,
                    isEnable = true
                ){

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Button Link Event
            PositiveTextButtonFlex(
                modifier = Modifier
                    .wrapContentHeight(),
                text = stringResource(id = R.string.label_link_event),
                isVisible = true,
                isEnable = true
            ){

            }
        }
    }

}

@ExperimentalPagerApi
@Preview
@Composable
private fun EventDetailScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        EventDetailScreen(navController = rememberNavController())
    }
}