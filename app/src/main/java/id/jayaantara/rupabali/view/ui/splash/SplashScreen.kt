package id.jayaantara.rupabali.view.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.Route
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.background
            )
    ) {

        // References
        val (
            logoRupaBaliIcon
        ) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(logoRupaBaliIcon){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ) {
            var startAnimation by remember { mutableStateOf(false) }
            val alphaAnim = animateFloatAsState(
                targetValue = if (startAnimation) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 3000
                )
            )

            LaunchedEffect(key1 = true) {
                startAnimation = true
                delay(4000)
                navController.popBackStack()
                navController.navigate(Route.ON_BOARDING)
            }

            Icon(
                modifier = Modifier.alpha(
                    alpha = alphaAnim.value
                ),
                painter = painterResource(id = R.drawable.rupa_bali_logo_03),
                contentDescription = "",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        SplashScreen(navController = rememberNavController())
    }
}