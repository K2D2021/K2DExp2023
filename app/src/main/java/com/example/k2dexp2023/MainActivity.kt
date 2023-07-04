package com.example.k2dexp2023

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.k2dexp2023.ui.theme.K2DExp2023Theme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.sign

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

                    Column(
                        modifier = Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var valueCounter by remember {
                            mutableStateOf(0)
                        }

                        CounterButton(value = valueCounter.toString(),
                            onValueIncreaseClick = { valueCounter += 1 },
                            onValueDecreaseClick = { valueCounter -= 1 },
                            onValueClearClick = { valueCounter = 0 }
                        )
                    }
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


/*@Composable
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
}*/

@Composable
private fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(200.dp)
            .height(80.dp)
    ) {
        val thumbOffsetX = remember {Animatable(0f)}

        ButtonContainer(
            thumbOffsetX = thumbOffsetX.value,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            onValueClearClick = onValueClearClick,
            modifier = Modifier
        )

        DraggableThumbButton(
            value = value,
            thumbOffsetX = thumbOffsetX,
//            onClick = onValueIncreaseClick,
//            onClick = null,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private const val ICON_BUTTON_ALPHA_INITIAL = 0.3f
private const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.3f
private const val CONTAINER_OFFSET_FACTOR = 0.1f

@Composable
fun ButtonContainer(
    thumbOffsetX: Float,
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
            .offset {
                IntOffset(
                    (thumbOffsetX * CONTAINER_OFFSET_FACTOR).toInt(),
                    0
                )
            }
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

private const val DRAG_LIMIT_HORIZONTAL_DP = 72

@SuppressLint("RememberReturnType")
@Composable
private fun DraggableThumbButton(
    value: String,
    thumbOffsetX: Animatable<Float, AnimationVector1D>,
//    onClick: () -> Unit,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dragLimitHorizontalPx = DRAG_LIMIT_HORIZONTAL_DP.dp.dpToPx()

    val scope = rememberCoroutineScope()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset {
                IntOffset(
                    thumbOffsetX.value.toInt(),
                    0
                )
            }
            .shadow(8.dp, shape = CircleShape)
            .size(64.dp)
            .clip(CircleShape)
//            .clickable { onClick() }
            .background(Color.Gray)
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()

                        do {
                            val event = awaitPointerEvent()
                            event.changes.forEach { pointerInputChange ->
                                scope.launch {
                                    val targetValue =
                                        thumbOffsetX.value + pointerInputChange.positionChange().x
                                    val targetValueWithinBounds = targetValue.coerceIn(
                                        -dragLimitHorizontalPx,
                                        dragLimitHorizontalPx
                                    )
                                    thumbOffsetX.snapTo(targetValueWithinBounds)
                                }
                            }
                        } while (event.changes.any { it.pressed })

                        if (thumbOffsetX.value.absoluteValue >= dragLimitHorizontalPx) {
                            if (thumbOffsetX.value.sign > 0) {
                                onValueIncreaseClick()
                            } else {
                                onValueDecreaseClick()
                            }
                        }

                        scope.launch {
                            if (thumbOffsetX.value != 0f) {
                                thumbOffsetX.animateTo(
                                    targetValue = 0f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioHighBouncy,
                                        stiffness = Spring.StiffnessMedium
                                    )
                                )
                            }
                        }
                    }
                }
            }
    ) {
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }




































