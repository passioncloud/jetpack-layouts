package net.passioncloud.compose_layout

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atMost
import net.passioncloud.compose_layout.ui.theme.ComposelayoutTheme

class MyConstraintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            ConstLayoutPreview()
        }
    }
}

@Preview
@Composable
fun ConstLayoutPreview() {
    ComposelayoutTheme {
//        Row(modifier=Modifier.width(207.dp).height(500.dp)) {
        ConstLayout()
//        }
    }
}

@Composable
fun ConstLayout() {
    Text("hey constrai")
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        // Create references for the composables to constrain
        val (button1, txt1, txt2, button2) = createRefs()

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Magnificent")
        }

        // Assign reference "txt" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text("Greetings...", Modifier.constrainAs(txt1) {
            top.linkTo(anchor = button1.bottom, margin = 15.dp)
            start.linkTo(anchor = button1.end, margin = 20.dp)
        })

        // Below txt1 but centered horizontally
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(anchor = txt1.bottom, margin = 4.dp)
                centerHorizontallyTo(parent)
            }
        ) {
            Text("Do it")
        }

//        val startBarrier = createStartBarrier(button1, margin=32.dp)
//        val endBarrier = createEndBarrier(button2, margin=32.dp)
//        var bottomBarrier = createBottomBarrier(button2, margin=32.dp)
        val startGuide = createGuidelineFromStart(42.dp)
        val endGuide = createGuidelineFromEnd(32.dp)
        val bottomGuide = createGuidelineFromBottom(32.dp)
        val (b1, b2, t1, t2) = createRefs()
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(b1) {
                start.linkTo(startGuide)
                end.linkTo(endGuide)
                top.linkTo(parent.top)
                bottom.linkTo(bottomGuide)
                width = Dimension.preferredWrapContent.atMost(77.dp)
//                width= Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            Text("Login kljslkdjlksjdljslkjsLKDJSLKDJLKADLKJAKDJFALDKFJLDKJFLADJFKAJALKDJFLKJAFAKLFJLKDJALKJFALDJFA")
        }


    }
}

@Preview
@Composable
fun MyDecoupledConstLayoutPreview() {
    ComposelayoutTheme {
        MyDecoupledConstLayout()
    }
}

@Composable
fun MyDecoupledConstLayout() {
    BoxWithConstraints {
        val constraints = decoupledConstraints()
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(modifier = Modifier.layoutId("t2"), text = "Juxtapon")
            Button(
                onClick = {},
                modifier = Modifier.layoutId("b1")
            ) {
                Text("Proceed")
            }
        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val startGuide = createGuidelineFromStart(40.dp)

        val btn1 = createRefFor("b1")
        constrain(btn1) {
            top.linkTo(parent.top, margin = 20.dp)
            start.linkTo(startGuide, margin = 30.dp)
            end.linkTo(parent.end, margin = 50.dp)
            width = Dimension.fillToConstraints
        }

        val t2 = createRefFor("t2")
        constrain(t2) {
            start.linkTo(startGuide)
            bottom.linkTo(parent.bottom, margin = 80.dp)
        }
    }
}

// intrinsics

@Preview
@Composable
fun IntrinsicComposablePreview() {
    ComposelayoutTheme {
        IntrinsicComposable()
    }
}

@Composable
fun IntrinsicComposable() {
    TwoTexts(text1 = "Piglet aljfldajdljfaldjf laj lkdjfalkdjf ldjf lajdflakj klajfldjf aafkldj k", text2 = "Eclair")
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(
        modifier = modifier
            // apply minimum intrinsic size height - given this width, whats the minimum height you can use to properly size you content
            // the query is performed before the children are measured
            // https://developer.android.com/codelabs/jetpack-compose-layouts#10
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .padding(start = 16.dp),
            text = text1
        )
        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(2.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

