package com.example.tooltip.ui.tooltip.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import com.assignment.tooltip.data.model.TooltipItem

@Composable
fun TooltipOverlay(
    tooltip: TooltipItem,
    targetRect: Rect?,
    onDismiss: () -> Unit,
    onNextClick: () -> Unit
) {
    if (targetRect == null) return

    val backdropOpacity = (tooltip.styling?.appearance?.backdropOpacity ?: 70) / 100f
    val backdropColor = Color.Black.copy(alpha = backdropOpacity)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { onDismiss() }
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.99f)
        ) {
            drawRect(color = backdropColor, size = size)

            drawRoundRect(
                color = Color.Transparent,
                topLeft = Offset(targetRect.left, targetRect.top),
                size = Size(targetRect.width, targetRect.height),
                cornerRadius = CornerRadius(16f, 16f),
                blendMode = BlendMode.Clear
            )
        }

        Box(
            modifier = Modifier.offset {
                IntOffset(
                    x = targetRect.left.toInt(),
                    y = (targetRect.bottom + 24).toInt()
                )
            }
        ) {
            TooltipCard(
                tooltip = tooltip,
                onNextClick = onNextClick
            )
        }
    }
}