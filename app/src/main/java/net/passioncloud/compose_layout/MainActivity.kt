package net.passioncloud.compose_layout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.passioncloud.compose_layout.ui.theme.ComposelayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotographerCardPreview()
        }
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

@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = {
            Text(text = "My nice app bar", maxLines = 2)
        }
    )
}

@Preview
@Composable
fun PhotographerCardPreview() {
    ComposelayoutTheme {
        PhotographerCard()
        MyTopAppBar()
    }
}

