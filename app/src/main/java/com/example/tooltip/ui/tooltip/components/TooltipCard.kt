package com.example.tooltip.ui.tooltip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.tooltip.data.model.TooltipItem
import com.example.tooltip.core.utlis.toComposeColor

@Composable
fun TooltipCard(
    tooltip: TooltipItem,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val styling = tooltip.styling
    val bgColor = styling?.appearance?.colors?.tooltip.toComposeColor(Color.Blue)
    val cornerRadius = styling?.appearance?.cornerRadius?.topLeft?.dp ?: 12.dp

    val ctaColor = styling?.cta?.container?.backgroundColor.toComposeColor(Color(0xFFF7921C))
    val titleColor = styling?.titleStyling?.color.toComposeColor(Color.White)
    val subtitleColor = styling?.subtitleStyling?.color.toComposeColor(Color.White)

    Box(
        modifier = modifier
            .widthIn(min = 200.dp, max = 300.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(bgColor)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = tooltip.titleText.orEmpty(),
                color = titleColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tooltip.subtitleText.orEmpty(),
                color = subtitleColor,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNextClick,
                colors = ButtonDefaults.buttonColors(containerColor = ctaColor),
                shape = RoundedCornerShape(styling?.cta?.cornerRadius?.topLeft?.dp ?: 8.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = tooltip.ctaText.orEmpty(),
                    color = styling?.cta?.text?.color.toComposeColor(Color.White)
                )
            }
        }
    }
}