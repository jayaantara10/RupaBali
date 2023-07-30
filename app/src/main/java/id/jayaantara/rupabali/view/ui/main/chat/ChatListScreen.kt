package id.jayaantara.rupabali.view.ui.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ChatListScreen(
    navController: NavController
){
    // Search State
    val searchBarHint = stringResource(id = R.string.label_chat_search_bar)
    var searchBarValue: String by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(top = 88.dp)
        ) {
            // References
            val (
                searchBar,
                chatList,
            ) = createRefs()

            //Search Bar
            OutlinedSearchBar(
                modifier = Modifier
                    .constrainAs(searchBar) {
                        top.linkTo(parent.top)
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

            //Chat List
            ChatList(
                modifier = Modifier
                    .constrainAs(chatList) {
                        top.linkTo(searchBar.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .padding(top = 16.dp),
                chatItems = chatItems,
                navController = navController
            )
        }
    }

    //TopAppbar
    ActionTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.chat_title),
        isButtonActionVisible = true,
        buttonActionIcon = R.drawable.ic_round_add_comment_24,
        onActionPress = {

        },
    )
}

@Composable
private fun ChatList(
    modifier: Modifier,
    chatItems: List<ChatItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues( start = 28.dp, end = 28.dp, bottom = 112.dp)
        ){
            items( items = chatItems){ chatItem ->
                ChatCard(
                    modifier = Modifier,
                    item = chatItem)
                {
                    toChatScreen(navController = navController)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun ChatListScreenPreview() {
    RupaBaliTheme(darkTheme = false) {
        ChatListScreen(navController = rememberNavController())
    }
}
