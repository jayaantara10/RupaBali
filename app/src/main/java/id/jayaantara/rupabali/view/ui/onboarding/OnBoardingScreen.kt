package id.jayaantara.rupabali.view.ui.onboarding

import android.graphics.drawable.Icon
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.Route
import id.jayaantara.rupabali.view.components.PagerIndicator
import id.jayaantara.rupabali.view.components.PositiveIconButton
import id.jayaantara.rupabali.view.components.PositiveTextButtonSmall
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.data.static.AddDataOnBoarding
import id.jayaantara.rupabali.view.ui.data.static.OnBoardingItem
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    navController: NavController
){
    val items = AddDataOnBoarding()
    OnBoardingPager(
        items = items,
        navController = navController
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnBoardingPager(
    items: List<OnBoardingItem>,
    navController: NavController
){

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        //Coroutine Scope
        val coroutineScope = rememberCoroutineScope()

        // References
        val (
            pagerOnBoarding,
            gradientOverlay,
            titleOnBoardingText,
            descriptionOnBoardingText,
            previousButton,
            nextButton,
            startButton,
            pagerIndicator
        ) = createRefs()

        //Button State
        //Previous Button State
        val previousButtonIcon = R.drawable.ic_round_arrow_back_ios_24
        var isPreviousButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isPreviousButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //Next Button State
        val nextButtonIcon = R.drawable.ic_round_arrow_forward_ios_24
        var isNextButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isNextButtonEnable: Boolean by remember { mutableStateOf(true ) }

        //Start Button State
        val startButtonLabel = stringResource(id = R.string.label_start)
        var isStartButtonVisible: Boolean by remember { mutableStateOf(true ) }
        val isStartButtonEnable: Boolean by remember { mutableStateOf(true ) }


        when (pagerState.currentPage) {
            0 -> {
                isPreviousButtonVisible = false
                isStartButtonVisible = false
                isNextButtonVisible = true
            }
            pagerState.pageCount - 1 -> {
                isPreviousButtonVisible = true
                Handler(Looper.getMainLooper()).postDelayed({
                    isNextButtonVisible = false
                }, 250)
                Handler(Looper.getMainLooper()).postDelayed({
                    isStartButtonVisible = true
                }, 750)
            }
            else -> {
                isPreviousButtonVisible = true
                Handler(Looper.getMainLooper()).postDelayed({
                    isStartButtonVisible = false
                }, 250)
                Handler(Looper.getMainLooper()).postDelayed({
                    isNextButtonVisible = true
                }, 750)
            }
        }

        // Pager On Boarding
        HorizontalPager(
            modifier = Modifier.constrainAs(pagerOnBoarding){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            state = pagerState
        ) { page ->
            Image(
                painter = painterResource(
                    id = items[page].image
                ),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop

            )
        }

        //Gradient Overlay
        Box(
            modifier = Modifier
                .constrainAs(gradientOverlay) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(
                    brush = Brush.verticalGradient(
                        0.4f to Color.Transparent,
                        0.7f to MaterialTheme.colors.background,
                        1.0f to MaterialTheme.colors.background,
                    )
                )
        )

        //Title OnBoarding
        Text(
            modifier = Modifier
                .constrainAs(titleOnBoardingText) {
                    bottom.linkTo(descriptionOnBoardingText.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(horizontal = 32.dp),
            style = Typography.h1,
            maxLines = 3,
            text = items[pagerState.currentPage].title
        )

        // Description OnBoarding
        Text(
            modifier = Modifier
                .constrainAs(descriptionOnBoardingText) {
                    bottom.linkTo(previousButton.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .height(112.dp)
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            style = Typography.body2,
            maxLines = 5,
            text = items[pagerState.currentPage].description
        )

        // Previous Button
        PositiveIconButton(
            modifier = Modifier
                .constrainAs(previousButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 32.dp, start = 32.dp, bottom = 32.dp)
                .size(56.dp),
            icon = previousButtonIcon,
            isVisible = isPreviousButtonVisible,
            isEnable = isPreviousButtonEnable
        ) {

            coroutineScope.launch {
                toPreviousPage(pagerState = pagerState)
            }
        }

        // Next Button and Start Button
        PositiveIconButton(
            modifier = Modifier
                .constrainAs(nextButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(top = 32.dp, end = 32.dp, bottom = 32.dp)
                .height(56.dp),
            icon = nextButtonIcon,
            isVisible = isNextButtonVisible,
            isEnable = isNextButtonEnable
        ) {

            coroutineScope.launch {
                toNextPage(pagerState = pagerState)
            }
        }

        PositiveTextButtonSmall(
            modifier = Modifier
                .constrainAs(startButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(top = 32.dp, end = 32.dp, bottom = 32.dp)
                .height(56.dp),
            text = startButtonLabel,
            isVisible = isStartButtonVisible,
            isEnable = isStartButtonEnable
        ) {
            navToAuthenticationScreen(navController = navController)
        }

        // Pager Indicator
        PagerIndicator(
            modifier = Modifier
                .constrainAs(pagerIndicator) {
                    top.linkTo(previousButton.top)
                    bottom.linkTo(previousButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(16.dp),
            items = items,
            currentPage = pagerState.currentPage
        )
    }
}

private fun navToAuthenticationScreen(navController: NavController) {
    navController.popBackStack()
    navController.navigate(route = Route.AUTHENTICATION)
}

@OptIn(ExperimentalPagerApi::class)
private suspend fun toNextPage(pagerState: PagerState) {
    if (pagerState.currentPage != pagerState.pageCount -1) {
        pagerState.animateScrollToPage(pagerState.currentPage + 1)
    }
}

@OptIn(ExperimentalPagerApi::class)
private suspend fun toPreviousPage(pagerState: PagerState) {
    if (pagerState.currentPage != 0 ) {
        pagerState.animateScrollToPage(pagerState.currentPage - 1)
    }
}

@Preview
@Composable
private fun OnBoardingScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        OnBoardingScreen(navController = rememberNavController())
    }
}
