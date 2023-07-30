package id.jayaantara.rupabali.view.ui.main

import androidx.navigation.NavController

fun toBackScreen(navController: NavController) {
    navController.popBackStack()
}

fun toArtworkScreen(navController: NavController) {
    navController.navigate(MainRoute.ARTWORK)
}

fun toAchievementDetailScreen(navController: NavController) {
    navController.navigate(MainRoute.ARTWORK_ACHIEVEMENT_DETAIL)
}

fun toNewsDetailScreen(navController: NavController) {
    navController.navigate(MainRoute.NEWS_DETAIL)
}

fun toEventDetailScreen(navController: NavController) {
    navController.navigate(MainRoute.EVENT_DETAIL)
}

fun toChatScreen(navController: NavController) {
    navController.navigate(MainRoute.CHAT)
}

fun toArtworkSubmissionListScreen(navController: NavController){
    navController.navigate(MainRoute.ARTWORK_SUBMISSION_LIST)
}

fun toArtworkSubmissionScreen(navController: NavController){
    navController.navigate(MainRoute.ARTWORK_SUBMISSION)
}