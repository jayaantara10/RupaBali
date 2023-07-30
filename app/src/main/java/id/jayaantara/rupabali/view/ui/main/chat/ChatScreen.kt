package id.jayaantara.rupabali.view.ui.main.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.components.ChatBottomBar
import id.jayaantara.rupabali.view.components.ChatCard
import id.jayaantara.rupabali.view.components.MessageCard
import id.jayaantara.rupabali.view.components.SimpleTopAppBar
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.main.ChatListScreen
import id.jayaantara.rupabali.view.ui.main.toBackScreen
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ChatScreen(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 56.dp)
    ) {
        // References
        val (
            messageList,
            chatBottomBar,
        ) = createRefs()

        //Message List
        MessageList(
            modifier = Modifier
                .constrainAs(messageList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(start = 32.dp, end = 32.dp, ),
            items = dummyMessageItems,
            navController = navController
        )

        //Chat Bottom Bar
        var chatTextFieldValue: String by rememberSaveable { mutableStateOf("") }
        ChatBottomBar(
            modifier = Modifier
                .constrainAs(chatBottomBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentHeight()
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
            hint = stringResource(id = R.string.hint_chat),
            text = chatTextFieldValue,
            onInputChange = {
                            chatTextFieldValue = it
            },
            onEmojiClick = { },
            onAttachClick = { },
            onSendClick = { }
        )

    }

    //TopAppbar
    SimpleTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = userItems[0].nickname,
        isButtonOptionVisible = true,
        isButtonBackVisible = true,
        onBackPress = {
            toBackScreen(navController = navController)
        },
    )
}

@Composable
private fun MessageList(
    modifier: Modifier,
    items: List<MessageItem>,
    navController: NavController
){
    Box(
        modifier = modifier
    ){
        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp, bottom = 112.dp)
        ){
            items( items = items){ item ->
                MessageCard(
                    modifier = Modifier,
                    item = item)
                {

                }
            }
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview(){
    RupaBaliTheme(darkTheme = false) {
        ChatScreen(navController = rememberNavController())
    }
}