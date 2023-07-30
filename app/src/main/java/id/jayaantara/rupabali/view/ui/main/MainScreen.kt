package id.jayaantara.rupabali.view.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.jayaantara.rupabali.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.view.theme.RupaBaliTheme

@ExperimentalPagerApi
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()
){

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ){
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavController){
    val items = ArrayList<BottomNavItem>()
    items.add(
        BottomNavItem(
            route = MainRoute.HOME,
            description = stringResource(id = R.string.home_title),
            iconSelected = R.drawable.ic_round_home_24,
            iconUnselected = R.drawable.ic_outline_home_24
        )
    )

    items.add(
        BottomNavItem(
            route = MainRoute.FAVORITE_ARTWORK,
            description = stringResource(id = R.string.favorite_artwork_title),
            iconSelected = R.drawable.ic_round_favorite_24,
            iconUnselected = R.drawable.ic_outline_favorite_border_24
        )
    )

    items.add(
        BottomNavItem(
            route = MainRoute.CHAT_LIST,
            description = stringResource(id = R.string.chat_title),
            iconSelected = R.drawable.ic_round_chat_bubble_24,
            iconUnselected = R.drawable.ic_outline_chat_bubble_outline_24
        )
    )

    items.add(
        BottomNavItem(
            route = MainRoute.PROFILE,
            description = stringResource(id = R.string.profile_title),
            iconSelected = R.drawable.ic_round_person_24,
            iconUnselected = R.drawable.ic_outline_person_24
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = items.any { it.route == currentDestination?.route}

    if(bottomBarDestination){
        BottomNavigation(
            modifier = Modifier
                .height(96.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp)),
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 16.dp
        ) {
            items.forEach{ item ->
                AddItem(
                    item = item,
                    currentDestination = currentDestination,
                    navController = navController)
            }
        }
    }

}

@Composable
private fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavController
){
    val isSelected = currentDestination?.hierarchy?.any{
        it.route == item.route
    } == true

    BottomNavigationItem(
        icon = {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                painter = painterResource(id = if(isSelected) item.iconSelected else item.iconUnselected),
                contentDescription = item.description
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == item.route
        } == true,
        onClick = {
            navController.navigate(item.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = false
    )
}

@ExperimentalPagerApi
@Preview
@Composable
private fun DashboardScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        MainScreen()
    }
}
