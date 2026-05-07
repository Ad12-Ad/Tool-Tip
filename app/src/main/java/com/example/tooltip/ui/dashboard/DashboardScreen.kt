package com.example.tooltip.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tooltip.ui.tooltip.TooltipIntent
import com.example.tooltip.ui.tooltip.TooltipViewModel
import com.example.tooltip.ui.tooltip.components.TooltipOverlay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: TooltipViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var targetCoordinates by remember { mutableStateOf(mapOf<String, Rect>()) }

    Box(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            topBar = { TopAppBar(title = { Text("Hi, Prem") }) }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Feedback") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                val bounds = coordinates.boundsInRoot()
                                targetCoordinates = targetCoordinates + ("feedback_input" to bounds)
                            }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            val bounds = coordinates.boundsInRoot()
                            targetCoordinates = targetCoordinates + ("dropdown" to bounds)
                        }
                    ) {
                        Text("Red Dropdown")
                    }
                }
            }
        }

        if (state.isTooltipVisible) {
            val currentTooltip = state.currentTooltip

            if (currentTooltip != null) {
                val activeTargetRect = targetCoordinates[currentTooltip.target]

                TooltipOverlay(
                    tooltip = currentTooltip,
                    targetRect = activeTargetRect,
                    onDismiss = { viewModel.processIntent(TooltipIntent.OnDismiss) },
                    onNextClick = { viewModel.processIntent(TooltipIntent.OnNextClicked) }
                )
            }
        }
    }
}