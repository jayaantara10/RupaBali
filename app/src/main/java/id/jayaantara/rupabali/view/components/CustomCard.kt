package id.jayaantara.rupabali.view.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.theme.*
import id.jayaantara.rupabali.view.ui.data.dummy.*
import id.jayaantara.rupabali.view.ui.data.static.UserCategoryItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LargeCardMenu(
    modifier: Modifier,
    cover: Painter,
    title: String,
    onClick: (() -> Unit)
){

    Box(
        modifier = modifier
    ){
        Card(
            modifier = Modifier
                .height(248.dp)
                .width(144.dp)
                .clickable { onClick },
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
            onClick = onClick
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Image(
                    painter = cover,
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                0.2f to Color.Transparent,
                                0.99f to Color.Black,
                                1.0f to Color.Black,
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        style = Typography.h3,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SmallCardMenu(
    modifier: Modifier,
    cover: Painter,
    title: String,
    onClick: (() -> Unit)
){
    Box(
        modifier = modifier
    ){
        Card(
            modifier = Modifier
                .height(120.dp)
                .width(144.dp)
                .clickable { onClick },
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
            onClick = onClick
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Image(
                    painter = cover,
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                0.2f to Color.Transparent,
                                0.99f to Color.Black,
                                1.0f to Color.Black,
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        style = Typography.h3,
                        color = Color.White
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCardSlider(
    modifier: Modifier,
    item: NewsItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .width(296.dp)
            .height(144.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = Typography.h3,
                    color = Color.White
                )

                Text(
                    text = item.date,
                    style = Typography.body2,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularArtworkCardSlider(
    modifier: Modifier,
    item: PopularArtworkItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .width(296.dp)
            .height(376.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = item.like,
                    style = Typography.body1,
                    color = Color.White
                )

                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    tint = Color.White,
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = Typography.h2,
                    color = Color.White
                )

                Text(
                    text = item.artist,
                    style = Typography.body1,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryCardSmall(
    modifier: Modifier,
    item: FineArtCategoryItem,
    isSelected: Boolean,
    onClick: (() -> Unit)
){

    val gradient: Float by animateFloatAsState(if (isSelected) 0.8f else 0f)
    val lineWidth: Dp by animateDpAsState(if (isSelected) 64.dp else 0.dp)

    Card(
        modifier = modifier
            .width(136.dp)
            .height(48.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.0f to Color.Transparent,
                            gradient to Color.Black,
                            1.0f to Color.Black,
                        ),
                        alpha = 0.6f
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    text = item.category,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .width(64.dp)
                ){
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(lineWidth)
                            .background(Color.White)
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCategoryCard(
    modifier: Modifier,
    item: UserCategoryItem,
    isSelected: Boolean,
    onClick: (() -> Unit)
){

    val gradient: Float by animateFloatAsState(if (isSelected) 0.8f else 0f)
    val lineWidth: Dp by animateDpAsState(if (isSelected) 64.dp else 0.dp)

    Card(
        modifier = modifier
            .width(136.dp)
            .height(48.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = painterResource(item.image),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.0f to Color.Transparent,
                            gradient to Color.Black,
                            1.0f to Color.Black,
                        ),
                        alpha = 0.6f
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    text = item.category,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .width(64.dp)
                ){
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(lineWidth)
                            .background(Color.White)
                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtworkCard(
    modifier: Modifier,
    item: ArtworkItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(248.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = item.like,
                    style = Typography.body1,
                    color = Color.White
                )

                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    tint = Color.White,
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = Typography.h3,
                    color = Color.White
                )

                Text(
                    text = item.artist,
                    style = Typography.body2,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtworkCategoryCard(
    modifier: Modifier,
    item: ArtworkCategoryItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(248.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.category,
                    style = Typography.h3,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatCard(
    modifier: Modifier,
    item: ChatItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
                .padding(16.dp)
        ) {

            val (
                profilePicture,
                nicknameText,
                messageText,
                dateText,
                unreadCounterText,
            ) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(profilePicture) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .size(56.dp)
                    .clip(shape = CircleShape),
                painter = rememberAsyncImagePainter(item.profilePicture),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(nicknameText) {
                        top.linkTo(parent.top)
                        start.linkTo(profilePicture.end)
                        end.linkTo(dateText.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.h5,
                textAlign = TextAlign.Left,
                maxLines = 1,
                text = item.name
            )
            Text(
                modifier = Modifier
                    .constrainAs(messageText) {
                        top.linkTo(nicknameText.bottom)
                        start.linkTo(profilePicture.end)
                        end.linkTo(dateText.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.body2,
                textAlign = TextAlign.Left,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = item.chat
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateText) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                style = Typography.body2,
                textAlign = TextAlign.Right,
                maxLines = 1,
                text = item.date
            )

            Box(
                modifier = Modifier
                    .constrainAs(unreadCounterText) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clip(shape = CircleShape)
                    .background(color = MaterialTheme.colors.primary)
                    .size(28.dp)
            ){
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.Center),
                    style = Typography.body2,
                    color = MaterialTheme.colors.background,
                    textAlign = TextAlign.Right,
                    maxLines = 1,
                    text = item.unreadCounter.toString()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(
    modifier: Modifier,
    item: NewsItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = Typography.h3,
                    color = Color.White
                )

                Text(
                    text = item.date,
                    style = Typography.body2,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FineArtTypeCard(
    modifier: Modifier,
    item: FineArtCategoryItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.category,
                    style = Typography.h3,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventCard(
    modifier: Modifier,
    item: EventItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            0.2f to Color.Transparent,
                            0.99f to Color.Black,
                            1.0f to Color.Black,
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = Typography.h3,
                    color = Color.White
                )

                Text(
                    text = item.date,
                    style = Typography.body2,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCard(
    modifier: Modifier,
    item: UserItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
                .padding(16.dp)
        ) {

            val (
                profilePicture,
                nicknameText,
                messageText,
                dateText,
                unreadCounterText,
            ) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(profilePicture) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .size(56.dp)
                    .clip(shape = CircleShape),
                painter = rememberAsyncImagePainter(item.profilePicture),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(nicknameText) {
                        top.linkTo(parent.top)
                        start.linkTo(profilePicture.end)
                        end.linkTo(dateText.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.h3,
                textAlign = TextAlign.Left,
                maxLines = 1,
                text = item.nickname
            )

            Text(
                modifier = Modifier
                    .constrainAs(messageText) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(profilePicture.end)
                        end.linkTo(dateText.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.body2,
                textAlign = TextAlign.Left,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = item.username
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateText) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                style = Typography.body2,
                textAlign = TextAlign.Right,
                maxLines = 1,
                text = item.role
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCard(
    modifier: Modifier,
    imageUrl: String,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ){
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OwnershipHistoryCard(
    modifier: Modifier,
    item: OwnershipHistoryItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
                .padding(16.dp)
        ) {

            val (
                ownerText,
                dateRangeText,
            ) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(ownerText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                style = Typography.h3,
                textAlign = TextAlign.Left,
                maxLines = 1,
                text = item.owner
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateRangeText) {
                        top.linkTo(ownerText.bottom)
                        start.linkTo(parent.start)
                    },
                style = Typography.body2,
                textAlign = TextAlign.Right,
                maxLines = 1,
                text = item.dateRange
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AchievementCard(
    modifier: Modifier,
    item: AchievementItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
                .padding(16.dp)
        ) {

            val (
                ownerText,
                dateRangeText,
            ) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(ownerText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                style = Typography.h3,
                textAlign = TextAlign.Left,
                maxLines = 1,
                text = item.title
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateRangeText) {
                        top.linkTo(ownerText.bottom)
                        start.linkTo(parent.start)
                    },
                style = Typography.body2,
                textAlign = TextAlign.Right,
                maxLines = 1,
                text = item.date
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageCard(
    modifier: Modifier,
    item: MessageItem,
    onClick: () -> Unit
){
    if (item.isSender){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 32.dp),
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 12.dp, bottomEnd = 0.dp),
            elevation = 4.dp,
            onClick = onClick
        ){
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBrown)
                    .padding(16.dp)
            ) {
                val (
                    messageText,
                    timeText,
                    readIcon,
                ) = createRefs()

                Text(
                    modifier = Modifier
                        .constrainAs(messageText) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        },
                    style = Typography.body2,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    text = item.message
                )

                Text(
                    modifier = Modifier
                        .constrainAs(timeText) {
                            top.linkTo(messageText.bottom)
                            end.linkTo(parent.end)
                        }
                        .wrapContentSize(),
                    style = Typography.body2,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    text = item.date
                )

                Box(
                    modifier = Modifier
                        .constrainAs(readIcon) {
                            top.linkTo(timeText.top)
                            bottom.linkTo(timeText.bottom)
                            end.linkTo(timeText.start)
                            height = Dimension.fillToConstraints
                        },
                ){
                    when (item.status){
                        "READ" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_check_24),
                                tint = Gold200,
                                contentDescription = ""
                            )
                        }
                        "UNREAD" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_check_24),
                                contentDescription = ""
                            )
                        }
                        "PENDING" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_access_time_24),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    } else {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, end = 32.dp),
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 12.dp),
            elevation = 4.dp,
            onClick = onClick
        ){
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brown)
                    .padding(16.dp)
            ) {
                val (
                    messageText,
                    timeText,
                ) = createRefs()

                Text(
                    modifier = Modifier
                        .constrainAs(messageText) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        },
                    style = Typography.body2,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    text = item.message
                )

                Text(
                    modifier = Modifier
                        .constrainAs(timeText) {
                            top.linkTo(messageText.bottom)
                            end.linkTo(parent.end)
                        }
                        .wrapContentSize(),
                    style = Typography.body2,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    text = item.date
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtworkSubmissionCard(
    modifier: Modifier,
    item: ArtworkItem,
    onClick: (() -> Unit)
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
                .padding(16.dp)
        ) {

            val (
                artworkImage,
                verifNumber,
                title,
                createdAt,
                updatedAt,
                status,
            ) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(artworkImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .width(80.dp)
                    .height(120.dp)
                    .clip(shape = RoundedCornerShape(12.dp)),
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(verifNumber) {
                        top.linkTo(parent.top)
                        start.linkTo(artworkImage.end)
                        end.linkTo(status.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.body2,
                textAlign = TextAlign.Left,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = item.verificationNumber
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(verifNumber.bottom)
                        start.linkTo(artworkImage.end)
                        end.linkTo(status.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                style = Typography.h3,
                textAlign = TextAlign.Left,
                maxLines = 2,
                text = item.title
            )

            Text(
                modifier = Modifier
                    .constrainAs(createdAt) {
                        top.linkTo(title.bottom)
                        start.linkTo(artworkImage.end)
                        end.linkTo(status.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.body2,
                textAlign = TextAlign.Left,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = stringResource(id = R.string.label_createdAt) + item.createdAt
            )

            Text(
                modifier = Modifier
                    .constrainAs(updatedAt) {
                        top.linkTo(createdAt.bottom)
                        start.linkTo(artworkImage.end)
                        end.linkTo(status.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp),
                style = Typography.body2,
                textAlign = TextAlign.Left,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = stringResource(id = R.string.label_updatedAt) + item.updatedAt
            )

            Text(
                modifier = Modifier
                    .constrainAs(status) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                style = Typography.body2,
                textAlign = TextAlign.Right,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                text = item.verificationStatus
            )
        }
    }
}

@Preview
@Composable
private fun CardPreview() {
    RupaBaliTheme(darkTheme = false) {
        ArtworkSubmissionCard(
            modifier = Modifier,
            item = artworkItems[0])
        {

        }
    }
}