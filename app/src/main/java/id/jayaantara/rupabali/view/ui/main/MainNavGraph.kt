package id.jayaantara.rupabali.view.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.view.Route
import id.jayaantara.rupabali.view.ui.main.artwork.*
import id.jayaantara.rupabali.view.ui.main.chat.ChatScreen
import id.jayaantara.rupabali.view.ui.main.education.ArtworkCategoryScreen
import id.jayaantara.rupabali.view.ui.main.education.EducationScreen
import id.jayaantara.rupabali.view.ui.main.education.FineArtCategoryScreen
import id.jayaantara.rupabali.view.ui.main.event.EventDetailScreen
import id.jayaantara.rupabali.view.ui.main.event.EventListScreen
import id.jayaantara.rupabali.view.ui.main.home.HomeScreen
import id.jayaantara.rupabali.view.ui.main.news.NewsDetailScreen
import id.jayaantara.rupabali.view.ui.main.news.NewsListScreen
import id.jayaantara.rupabali.view.ui.main.profile.AccountManagementScreen
import id.jayaantara.rupabali.view.ui.main.profile.ChangePasswordScreen
import id.jayaantara.rupabali.view.ui.main.profile.EditProfileScreen
import id.jayaantara.rupabali.view.ui.main.profile.VerificationUserScreen
import id.jayaantara.rupabali.view.ui.main.submission.ArtworkSubmissionListScreen
import id.jayaantara.rupabali.view.ui.main.user.UserListScreen

@ExperimentalPagerApi
@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Route.MAIN,
        startDestination = MainRoute.HOME,
    ){
        composable(
            route = MainRoute.HOME
        ){
            HomeScreen(navController = navController)
        }

        composable(
            route = MainRoute.FAVORITE_ARTWORK
        ){
            FavoriteArtworkScreen(navController = navController)
        }

        composable(
            route = MainRoute.CHAT_LIST
        ){
            ChatListScreen(navController = navController)
        }

        composable(
            route = MainRoute.PROFILE
        ){
            ProfileScreen(navController = navController)
        }

        composable(
            route = MainRoute.EDUCATION
        ){
            EducationScreen(navController = navController)
        }

        composable(
            route = MainRoute.NEWS_LIST
        ){
            NewsListScreen(navController = navController)
        }

        composable(
            route = MainRoute.EVENT_LIST
        ){
            EventListScreen(navController = navController)
        }

        composable(
            route = MainRoute.USER_LIST
        ){
            UserListScreen(navController = navController)
        }

        composable(
            route = MainRoute.FINE_ART_CATEGORY_DETAILS
        ){
            FineArtCategoryScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_CATEGORY_DETAILS
        ){
            ArtworkCategoryScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK
        ){
            ArtworkScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_DETAIL
        ){
            ArtworkDetailScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_OWNERSHIP_HISTORY
        ){
            ArtworkOwnershipHistoryScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_ACHIEVEMENT_LIST
        ){
            ArtworkAchievementListScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_ACHIEVEMENT_DETAIL
        ){
            ArtworkAchievementDetailScreen(navController = navController)
        }

        composable(
            route = MainRoute.NEWS_DETAIL
        ){
            NewsDetailScreen(navController = navController)
        }

        composable(
            route = MainRoute.EVENT_DETAIL
        ){
            EventDetailScreen(navController = navController)
        }

        composable(
            route = MainRoute.CHAT
        ){
            ChatScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_SUBMISSION_LIST
        ){
            ArtworkSubmissionListScreen(navController = navController)
        }

        composable(
            route = MainRoute.ARTWORK_SUBMISSION
        ){
            ArtworkSubmissionScreen(navController = navController)
        }

        composable(
            route = MainRoute.MANAGEMENT_ACCOUNT
        ){
            AccountManagementScreen(navController = navController)
        }

        composable(
            route = MainRoute.EDIT_PROFILE
        ){
            EditProfileScreen(navController = navController)
        }

        composable(
            route = MainRoute.CHANGE_PASSWORD
        ){
            ChangePasswordScreen(navController = navController)
        }

        composable(
            route = MainRoute.USER_VERIFICATION
        ){
            VerificationUserScreen(navController = navController)
        }
    }
}

