package com.example.tooltip.domain.repository

import com.assignment.tooltip.data.model.TooltipResponse
import com.example.tooltip.core.network.NetworkResult

interface TooltipRepository {
    /**
     * Fetches the tooltip configuration data.
     * In a real app, this might hit a network API. For this assignment,
     * the implementation can parse a local JSON string or file.
     */
    suspend fun getTooltipConfig(): NetworkResult<TooltipResponse>
}