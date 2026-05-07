package com.example.tooltip.domain.usecase

import com.assignment.tooltip.data.model.TooltipResponse
import com.example.tooltip.core.network.NetworkResult
import com.example.tooltip.domain.repository.TooltipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTooltipDataUseCase(
    private val repository: TooltipRepository
) {
    operator fun invoke(): Flow<NetworkResult<TooltipResponse>> = flow {
        val result = repository.getTooltipConfig()
        emit(result)
    }
}