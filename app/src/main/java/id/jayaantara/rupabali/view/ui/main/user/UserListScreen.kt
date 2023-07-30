package id.jayaantara.rupabali.view.ui.main.user

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.components.*
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.data.static.UserCategoryItem

@Composable
fun UserListScreen(
    navController: NavController
){
    // Search State
    val searchBarHint = stringResource(id = R.string.label_user_search_bar)
    var searchBarValue: String by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {


        // References
        val (
            topAppbar,
            searchBar,
            categoryList,
            userList,
        ) = createRefs()

        //TopAppbar
        SimpleTopAppBar(
            modifier = Modifier
                .constrainAs(topAppbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentHeight(),
            title = stringResource(id = R.string.user_title),
            isButtonBackVisible= true,
            onBackPress= {
                navToHomeScreen(navController = navController)
            }
        )

        //Search Bar
        OutlinedSearchBar(
            modifier = Modifier
                .constrainAs(searchBar) {
                    top.linkTo(topAppbar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp),
            hint = searchBarHint,
            text = searchBarValue,
            onInputChange = {
                searchBarValue = it
            }
        ) {
            searchBarValue = ""
        }

        //Category List
        CategoryList(
            modifier = Modifier
                .constrainAs(categoryList) {
                    top.linkTo(searchBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            navController = navController
        )

        //User List
        UserList(
            modifier = Modifier
                .constrainAs(userList) {
                    top.linkTo(categoryList.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            userItems = userItems,
            navController = navController
        )
    }
}

@Composable
private fun CategoryList(
    modifier: Modifier,
    navController: NavController
){
    val category = stringArrayResource(id = R.array.user_category_label)

    val userCategoryItems = listOf(
        UserCategoryItem(
            category = category[0],
            image = R.drawable.cover_category_usert_1
        ),

        UserCategoryItem(
            category = category[1],
            image = R.drawable.cover_category_usert_2
        ),

        UserCategoryItem(
            category = category[2],
            image = R.drawable.cover_category_usert_3
        ),

        UserCategoryItem(
            category = category[3],
            image = R.drawable.cover_category_usert_4
        ),
    )

    var selectedCategory: String by rememberSaveable { mutableStateOf(userCategoryItems[0].category) }

    val context = LocalContext.current

    Box(
        modifier = modifier
    ){
        LazyRow(
            contentPadding = PaddingValues( horizontal = 28.dp)
        ){
            items( items = userCategoryItems){ userCategoryItem ->
                UserCategoryCard(
                    modifier = Modifier
                        .padding(4.dp),
                    item = userCategoryItem,
                    isSelected = selectedCategory.equals(userCategoryItem.category)
                ) {
                    selectedCategory = userCategoryItem.category
                    Log.d("Test", selectedCategory)
                }
            }
        }
    }
}

@Composable
private fun UserList(
    modifier: Modifier,
    userItems: List<UserItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues( start = 28.dp, end = 28.dp, bottom = 36.dp)
        ){
            items( items = userItems){ userItem ->
                UserCard(
                    modifier = Modifier,
                    item = userItem)
                {

                }
            }
        }
    }
}

fun navToHomeScreen(navController: NavController) {
    navController.popBackStack()
}

@ExperimentalPagerApi
@Preview
@Composable
private fun NewsListScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        UserListScreen(navController = rememberNavController())
    }
}