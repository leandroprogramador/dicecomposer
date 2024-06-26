package com.leandro.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leandro.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
    Dice(1,Modifier.align(Alignment.CenterEnd))
    Dice(2,Modifier.align(Alignment.BottomStart))
    Dice(3,Modifier.align(Alignment.BottomEnd))
    Dice(4,Modifier.align(Alignment.TopStart))
    Dice(5,Modifier.align(Alignment.TopEnd))
    Dice(6,Modifier.align(Alignment.CenterStart))
       Button(modifier = Modifier
           .align(Alignment.Center)
           .offset(y = (100).dp)
           , onClick = {

       }) {
           Text(text = "Jogar")
       }
    }
}

@Composable
fun Dice(number : Int, modifier: Modifier) {
    Canvas(modifier = modifier.size(96.dp, 96.dp)){
        drawRoundRect(Color.White,
            cornerRadius = CornerRadius(20f, 20f),
            topLeft = Offset(10f, 10f),
            size = this.size)

        bullet(number)

    }
}


fun DrawScope.bullet(number : Int) {
    when(number) {
        1-> {
            center()
        }
        2 -> {
            topRight()
            leftBottom()
        }
        3-> {
            topRight()
            center()
            leftBottom()
        }
        4 -> {
            topLeft()
            topRight()
            leftBottom()
            rightBottom()
        }

        5 -> {
            topLeft()
            topRight()
            center()
            leftBottom()
            rightBottom()
        }

        6 -> {
            topLeft()
            topRight()
            centerLeft()
            centerRight()
            leftBottom()
            rightBottom()
        }
    }
}

fun DrawScope.circle(offset : (Float) -> Offset) {
    val radius = Dp(20f).value
    drawCircle(Color.Black, radius =radius, center = offset(radius))
}

fun DrawScope.center() {
    circle(){
        Offset((size.width / 2f) + (it/2f), (size.height/2f)+ (it/2f))
    }
}

fun DrawScope.centerLeft() {
    circle(){
        Offset(it*2f, (size.height/2f)+ (it/2f))
    }
}

fun DrawScope.centerRight() {
    circle(){
        Offset(size.width  - it, (size.height/2f)+ (it/2f))
    }
}

fun DrawScope.topRight() {
    circle(){
        Offset(size.width - it , it*2f)
    }
}

fun DrawScope.topLeft() {
    circle(){
        Offset(it*2f , it*2f)
    }
}

fun DrawScope.rightBottom() {
    circle(){
        Offset(size.width-it , size.height-it)
    }
}


fun DrawScope.leftBottom() {
    circle(){
        Offset( it*2f , size.height- it)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceGameTheme {
       Surface(modifier = Modifier.fillMaxSize()) {
           App()
       }
    }
}