package net.passioncloud.compose_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import net.passioncloud.compose_layout.ui.theme.ComposelayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppPreview()
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    ComposelayoutTheme {
        MyApp();
    }
}

@Composable
fun MyApp() {
    Scaffold(
        topBar = { MyAppBar() },
       // drawerContent = { MyDrawerContent() },
        bottomBar = { MyBottomAppBar() }
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}

@Composable
fun MyBottomAppBar() {
    BottomAppBar {
        BottomNavigationItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(Icons.Filled.Add, contentDescription = "Add one")
            })
        BottomNavigationItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(Icons.Filled.Star, contentDescription = "Star me")
            }
        )
    }
}

@Composable
fun MyDrawerContent() {
    ModalDrawer(drawerContent = {
        Text("Minute main")
        Text("Coca cola")
    }) {

    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        title = {
            Text(text = "Welcome to our app")
        },
        actions = {
            IconButton(onClick = {/* Do sth */ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "My fav")
            }
        }
    )
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    SimpleList()
}

@Composable
fun SimpleList() {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Row {
        Button(onClick = {
            coroutineScope.launch {
                scrollState.animateScrollToItem(0)
            }
        }) {
            Text("Scroll to first")
        }
        Button(onClick = {
            coroutineScope.launch {
                scrollState.animateScrollToItem(9999)
            }
        }) {
            Text("Scroll to last")
        }
    }
    LazyColumn(state = scrollState) {
        items(10000) {
            ImageListItem(index = it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data="https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "My nice logo",
            modifier = Modifier.size(38.dp)
        )
        Spacer(Modifier.width(10.0.dp))
        Text("Item $index", style=MaterialTheme.typography.subtitle1)
    }
}




@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {})
            .padding(16.dp)
    ) {
        Row {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                // image goes here
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Text(text = "Alfred Muwanguzi", fontWeight = FontWeight.Bold)
                // LocalContentAlpha is defining opacity level of its children
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("5 years ago", style = MaterialTheme.typography.body2)
                }
            }
        }

        MyBtn {
            Row {
                Row {
                    Text("Amageddon")
                }
            }
        }

    }
}


@Composable
fun MyBtn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Button(onClick = {}, modifier = modifier) {
        Row {
            Text("Ring")
            content()
        }
    }
}


@Preview
@Composable
fun PhotographerCardPreview() {
    ComposelayoutTheme {
        PhotographerCard()
    }
}

