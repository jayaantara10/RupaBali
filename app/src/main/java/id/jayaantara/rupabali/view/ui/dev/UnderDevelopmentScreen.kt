package id.jayaantara.rupabali.view.ui.dev

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.rememberNavController
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.Route
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography
import id.jayaantara.rupabali.view.ui.onboarding.OnBoardingScreen
import kotlinx.coroutines.delay

@Composable
fun UnderDevelopmentScreen(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.background
            )
    ) {

        // References
        val (
            logoRupaBaliIcon,
            messageText
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
            Icon(
                painter = painterResource(id = R.drawable.rupa_bali_logo_03),
                contentDescription = "",
                tint = MaterialTheme.colors.primary
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(messageText) {
                    top.linkTo(logoRupaBaliIcon.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 32.dp),
            style = Typography.body1,
            maxLines = 2,
            text = stringResource(id = R.string.under_development_page)
        )
    }
}

@Preview
@Composable
private fun UnderDevelopmentScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        UnderDevelopmentScreen()
    }
}