package id.jayaantara.rupabali.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import id.jayaantara.rupabali.R
import id.jayaantara.rupabali.view.theme.RupaBaliTheme
import id.jayaantara.rupabali.view.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImagePicker(
    modifier: Modifier,
    icon: Painter,
    text: String,
    imageUrl: String,
    onClick: (() -> Unit)
){

    val localDensity = LocalDensity.current
    var layoutWidth by remember {
        mutableStateOf(0.dp)
    }

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    layoutWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
                .height(layoutWidth)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    text = text,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

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
fun CircleImagePicker(
    modifier: Modifier,
    icon: Painter,
    text: String,
    imageUrl: String,
    onClick: (() -> Unit)
){

    val localDensity = LocalDensity.current
    var layoutWidth by remember {
        mutableStateOf(0.dp)
    }

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(layoutWidth.div(2f).toPx()))
            },
        shape = CircleShape,
        elevation = 4.dp,
        onClick = onClick
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    layoutWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
                .height(layoutWidth)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    text = text,
                    style = Typography.body2,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

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
fun VideoPicker(
    modifier: Modifier,
    icon: Painter,
    text: String,
    imageUrl: String,
    onClick: (() -> Unit)
){

    val localDensity = LocalDensity.current
    var layoutWidth by remember {
        mutableStateOf(0.dp)
    }

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    layoutWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
                .height(layoutWidth.div(2f))
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    text = text,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

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
fun LocationPicker(
    modifier: Modifier,
    imageUrl: String,
    icon: Painter,
    text: String,
    onClick: (() -> Unit)
){

    val localDensity = LocalDensity.current
    var layoutWidth by remember {
        mutableStateOf(0.dp)
    }

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    layoutWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
                .height(layoutWidth)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    text = text,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

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
fun DocumentPicker(
    modifier: Modifier,
    icon: Painter,
    text: String,
    imageUrl: String,
    onClick: (() -> Unit)
){

    val localDensity = LocalDensity.current
    var layoutWidth by remember {
        mutableStateOf(0.dp)
    }

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    layoutWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
                .height(layoutWidth)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    text = text,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

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
fun DetailArtworkPicker(
    modifier: Modifier,
    text: String,
    onClick: (() -> Unit)
){

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = Typography.button,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtworkAchievementPicker(
    modifier: Modifier,
    text: String,
    onClick: (() -> Unit)
){

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = Typography.button,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtworkOwnershipHistoryPicker(
    modifier: Modifier,
    text: String,
    onClick: (() -> Unit)
){

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = Typography.button,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IdentityCardPicker(
    modifier: Modifier,
    icon: Painter,
    text: String,
    imageUrl: String,
    onClick: (() -> Unit)
){

    val localDensity = LocalDensity.current
    var layoutWidth by remember {
        mutableStateOf(0.dp)
    }

    val stroke = Stroke(width = 10f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    )

    val dashedStrokeColor = MaterialTheme.colors.primary
    Card(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color = dashedStrokeColor, style = stroke, cornerRadius = CornerRadius(12.dp.toPx()))
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onClick
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    layoutWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
                .height(layoutWidth.div(2f))
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    text = text,
                    style = Typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun CustomImagePickerPreview(){
    RupaBaliTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ){
            CircleImagePicker(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_round_person_24),
                text = "",
                imageUrl = "",
                onClick = {}
            )
        }
    }
}