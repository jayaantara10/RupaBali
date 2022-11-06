package id.jayaantara.rupabali.ui.activity.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.ui.components.*
import id.jayaantara.rupabali.ui.model.OnBoardingData
import id.jayaantara.rupabali.ui.theme.RupaBaliTheme
import id.jayaantara.rupabali.ui.theme.Typography
import kotlinx.coroutines.launch

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RupaBaliTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    OnBoardingActivityView()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingActivityView(){
    val items = ArrayList<OnBoardingData>()
    items.add(
        OnBoardingData(
            image = R.drawable.gwk,
            title = stringResource(R.string.onboarding_title_1),
            description = stringResource(R.string.onboarding_description_1)
        )
    )

    items.add(
        OnBoardingData(
            image = R.drawable.lukisan,
            title = stringResource(R.string.onboarding_title_2),
            description = stringResource(R.string.onboarding_description_2)
        )
    )

    items.add(
        OnBoardingData(
            image = R.drawable.seniman,
            title = stringResource(R.string.onboarding_title_3),
            description = stringResource(R.string.onboarding_description_3)
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier.fillMaxWidth()
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier =  Modifier
){
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Image(
                    painter = painterResource(
                        id = item[page].image
                    ),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop

                )
            }
        }

        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .background(
                brush = Brush.verticalGradient(
                    0.4f to Color.Transparent,
                    0.8f to MaterialTheme.colors.background,
                    1.0f to MaterialTheme.colors.background,
                )
            )
            .fillMaxSize()
        ){
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(365.dp)
                .fillMaxWidth()
                .padding(32.dp)
            ) {
                Column {
                    Text(
                        style = Typography.h1,
                        maxLines = 3,
                        text = item[pagerState.currentPage].title
                    )

                    Text(
                        style = Typography.body1,
                        maxLines = 5,
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = item[pagerState.currentPage].description
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 32.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val scope = rememberCoroutineScope()
                        val context = LocalContext.current
                        var previousButtonVisible by remember { mutableStateOf(false) }

                        var nextButtonVisible by remember { mutableStateOf(false) }

                        var startButtonVisible by remember { mutableStateOf(false) }

                        AnimatedVisibility(
                            visible = previousButtonVisible,
                            enter = fadeIn(
                                initialAlpha = 0.4f,
                                animationSpec = tween(250, 0, LinearEasing)
                            ),
                            exit = fadeOut(
                                animationSpec = tween( 250, 0, LinearEasing)
                            )
                        ) {
                            CustomPositiveIconButton(
                                icon = R.drawable.ic_round_arrow_back_ios_24,
                            ) {

                                scope.launch {
                                    toPreviousPage(pagerState = pagerState)
                                }
                            }
                        }
                        
                        if (pagerState.currentPage == 0) {
                            previousButtonVisible = false
                            Box(modifier = Modifier.size(48.dp))
                        } else {
                            previousButtonVisible = true
                            Box(modifier = Modifier.size(48.dp))
                        }

                        AnimatedVisibility(
                            visible = nextButtonVisible,
                            enter = fadeIn(

                                initialAlpha = 0.0f,
                                animationSpec = tween(250, 0, LinearEasing)
                            ),
                            exit = fadeOut(
                                animationSpec = tween( 250, 0, LinearEasing)
                            )
                        ) {
                            CustomPositiveIconButton(
                                icon = R.drawable.ic_round_arrow_forward_ios_24,
                            ) {

                                scope.launch {
                                    toNextPage(pagerState = pagerState)
                                }
                            }
                        }

                        AnimatedVisibility(
                            visible = startButtonVisible,
                            enter = fadeIn(
                                initialAlpha = 0.0f,
                                animationSpec = tween(250, 0, LinearEasing)
                            ),
                            exit = fadeOut(
                                animationSpec = tween( 250, 0, LinearEasing)
                            )
                        ) {
                            CustomPositiveTextButtonSmall(
                                text = "Mulai",
                            ) {

                                scope.launch {
                                    toLoginActivity(context)
                                }
                            }
                        }

                        if (pagerState.currentPage == pagerState.pageCount - 1) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                nextButtonVisible = false
                            }, 250)
                            Handler(Looper.getMainLooper()).postDelayed({
                                startButtonVisible = true
                            }, 750)
                        } else {
                            Handler(Looper.getMainLooper()).postDelayed({
                                startButtonVisible = false
                            }, 250)
                            Handler(Looper.getMainLooper()).postDelayed({
                                nextButtonVisible = true
                            }, 750)
                        }

                    }

                }
                
//                PagerIndicator(
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter)
//                        .padding(16.dp),
//                    items = item,
//                    currentPage = pagerState.currentPage
//                )
            }

            val emailState = remember { TextFieldState() }

            EmailTextField(
                emailState = emailState
            )

        }

    }

}

@OptIn(ExperimentalPagerApi::class)
suspend fun toNextPage(pagerState: PagerState) {
    if (pagerState.currentPage != pagerState.pageCount -1) {
        pagerState.animateScrollToPage(pagerState.currentPage + 1)
    }
}

@OptIn(ExperimentalPagerApi::class)
suspend fun toPreviousPage(pagerState: PagerState) {
    if (pagerState.currentPage != 0 ) {
        pagerState.animateScrollToPage(pagerState.currentPage - 1)
    }
}

fun toLoginActivity(context: Context) {
    Toast.makeText(context, "Menuju Halaman Login", Toast.LENGTH_LONG).show()
}

@Preview
@Composable
fun OnBoardingActivityPreview() {
    RupaBaliTheme(darkTheme = false) {
        OnBoardingActivityView()
    }
}


