package id.jayaantara.rupabali.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.view.ui.authentication.AuthenticationScreen
import id.jayaantara.rupabali.view.ui.main.MainScreen
import id.jayaantara.rupabali.view.ui.onboarding.OnBoardingScreen
import id.jayaantara.rupabali.view.ui.register.user.RegisterUserScreen
import id.jayaantara.rupabali.view.ui.splash.SplashScreen

@ExperimentalPagerApi
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Route.ROOT,
        startDestination = Route.SPLASH,
    ){
        composable(
            route = Route.SPLASH
        ){
            SplashScreen(navController)
        }

        composable(
            route = Route.ON_BOARDING
        ){
            OnBoardingScreen(navController)
        }

        composable(
            route = Route.AUTHENTICATION
        ){
            AuthenticationScreen(navController = navController)
        }

        composable(
            route = Route.REGISTER_USER
        ){
            RegisterUserScreen(navController = navController)
        }

        composable(
            route = Route.MAIN
        ){
            MainScreen()
        }
    }
}