package com.example.k2dexp2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k2dexp2023.ui.theme.K2DExp2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            K2DExp2023Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var sliderPosition by remember {
                        mutableStateOf(30f)
                    }

                    var sliderPosition2 by remember {
                        mutableStateOf(30f)
                    }

                    val handlePositionChange = { position: Float -> sliderPosition = position }
                    val handlePositionChange2 = { position: Float -> sliderPosition2 = position }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ResizeableText("Yor are so cool!", sliderPosition)

                        Spacer(modifier = Modifier.height(10.dp))

                        SliderForTextSize(
                            sliderPosition = sliderPosition,
                            onPositionChange = handlePositionChange
                        )

                        Text(
                            style = MaterialTheme.typography.headlineLarge,
                            text = sliderPosition.toInt().toString() + "sp"
                        )

                        Spacer(modifier = Modifier.height(100.dp))

                        ResizeableText("It is true!", sliderPosition2)

                        Spacer(modifier = Modifier.height(10.dp))

                        SliderForTextSize(
                            sliderPosition = sliderPosition2,
                            onPositionChange = handlePositionChange2
                        )

                        Text(
                            style = MaterialTheme.typography.headlineMedium,
                            text = sliderPosition2.toInt().toString() + "sp"
                        )

                    }
                }
            }
        }
    }
}


@Composable
fun ResizeableText(displayedText: String = "Default text", textSize: Float = 10F) {
    Text(
        text = displayedText,
        fontSize = textSize.sp,
        fontWeight = FontWeight.Thin
    )
}

@Composable
fun SliderForTextSize(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(30.dp),
        valueRange = 30f..38f,
        value = sliderPosition,
        onValueChange = { onPositionChange(it) })
}