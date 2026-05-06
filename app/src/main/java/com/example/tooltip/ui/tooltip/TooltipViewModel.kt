package com.example.tooltip.ui.tooltip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tooltip.core.network.NetworkResult
import com.example.tooltip.domain.usecase.GetTooltipDataUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TooltipViewModel(
    private val getTooltipDataUseCase: GetTooltipDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TooltipState())
    val state: StateFlow<TooltipState> = _state.asStateFlow()

    private val _effect = Channel<TooltipEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        processIntent(TooltipIntent.FetchTooltipData)
    }

    fun processIntent(intent: TooltipIntent) {
        when (intent) {
            is TooltipIntent.FetchTooltipData -> fetchTooltipConfig()
            is TooltipIntent.OnNextClicked -> handleNextStep()
            is TooltipIntent.OnDismiss -> dismissTooltip()
        }
    }

    private fun fetchTooltipConfig() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            getTooltipDataUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                config = result.data,
                                isTooltipVisible = true,
                                currentStepIndex = 0
                            )
                        }
                    }
                    is NetworkResult.ApiError -> {
                        val errorMsg = result.errorMessage ?: "API Error Occurred"
                        _state.update { it.copy(isLoading = false, errorMessage = errorMsg) }
                        _effect.send(TooltipEffect.ShowToast(errorMsg))
                    }
                    is NetworkResult.NetworkError -> {
                        _state.update { it.copy(isLoading = false, errorMessage = result.message) }
                        _effect.send(TooltipEffect.ShowToast(result.message))
                    }
                    is NetworkResult.UnknownError -> {
                        val errorMsg = "An unknown error occurred"
                        _state.update { it.copy(isLoading = false, errorMessage = errorMsg) }
                        _effect.send(TooltipEffect.ShowToast(errorMsg))
                    }
                }
            }
        }
    }

    private fun handleNextStep() {
        val currentState = _state.value

        if (currentState.isLastStep) {
            dismissTooltip()
            viewModelScope.launch { _effect.send(TooltipEffect.TutorialCompleted) }
        } else {
            _state.update { it.copy(currentStepIndex = it.currentStepIndex + 1) }
        }
    }

    private fun dismissTooltip() {
        _state.update { it.copy(isTooltipVisible = false) }
    }
}