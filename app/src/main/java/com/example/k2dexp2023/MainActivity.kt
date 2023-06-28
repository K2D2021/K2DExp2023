package com.example.k2dexp2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
                    /*var sliderPosition by remember {
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

                    }*/
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

@Composable
fun CounterButton(
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        ButtonContainer(
            onValueDecreaseClick = { /*TODO*/ },
            onValueIncreaseClick = { /*TODO*/ },
            onValueClearClick = { /*TODO*/ },
            modifier = Modifier
        )

        DraggableThumbButton(
            value = value,
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private const val ICON_BUTTON_ALPHA_INITIAL = 0.3f
private const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.3f

@Composable
fun ButtonContainer(
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    clearButtonVisible: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(64.dp))
            .background(Color.Black.copy(alpha = CONTAINER_BACKGROUND_ALPHA_INITIAL))
            .padding(horizontal = 8.dp)
    ) {
        IconControlButton(
            icon = ImageVector.vectorResource(id = R.drawable.baseline_expand_more_24),
            contentDescription = "Decrease count",
            onClick = onValueDecreaseClick,
            tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
        )

        if (clearButtonVisible) {
            IconControlButton(
                icon = ImageVector.vectorResource(id = R.drawable.baseline_clear_24),
                contentDescription = "Clear count",
                onClick = onValueClearClick,
                tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
            )
        }
        IconControlButton(
            icon = ImageVector.vectorResource(id = R.drawable.baseline_expand_less_24),
            contentDescription = "Increase count",
            onClick = onValueIncreaseClick,
            tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
        )
    }
}

@Composable
private fun IconControlButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tintColor: Color = Color.White,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tintColor,
            modifier = Modifier.size(32.dp)
        )
    }
}




































