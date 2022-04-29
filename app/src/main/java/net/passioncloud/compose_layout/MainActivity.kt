package net.passioncloud.compose_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.passioncloud.compose_layout.ui.theme.ComposelayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppPreview()
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        topBar = { MyAppBar() },
        drawerContent = { MyDrawerContent() },
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
    Column(modifier) {
        Text(
            text = "Gwe musajja"
        )
        Text("When did you start college")
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

