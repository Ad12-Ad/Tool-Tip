package com.example.tooltip.ui.tooltip

import com.example.tooltip.data.model.TooltipItem
import com.example.tooltip.data.model.TooltipResponse

data class TooltipState(
    val isLoading: Boolean = false,
    val config: TooltipResponse? = null,
    val currentStepIndex: Int = 0,
    val isTooltipVisible: Boolean = false,
    val errorMessage: String? = null
) {
    val currentTooltip: TooltipItem?
        get() = config?.details?.tooltips?.getOrNull(currentStepIndex)

    val isLastStep: Boolean
        get() = currentStepIndex >= (config?.details?.tooltips?.size?.minus(1) ?: 0)
}

sealed interface TooltipIntent {
    data object FetchTooltipData : TooltipIntent
    data object OnNextClicked : TooltipIntent
    data object OnDismiss : TooltipIntent
}

sealed interface TooltipEffect {
    data class ShowToast(val message: String) : TooltipEffect
    data object TutorialCompleted : TooltipEffect
}