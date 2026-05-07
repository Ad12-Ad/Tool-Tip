package com.example.tooltip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tooltip.data.repository.TooltipRepositoryImpl
import com.example.tooltip.domain.usecase.GetTooltipDataUseCase
import com.example.tooltip.ui.dashboard.DashboardScreen
import com.example.tooltip.ui.tooltip.TooltipViewModel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {

    private val viewModel: TooltipViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val gson = Gson()
                val repository = TooltipRepositoryImpl(gson)
                val useCase = GetTooltipDataUseCase(repository)
                return TooltipViewModel(useCase) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen(viewModel = viewModel)
                }
            }
        }
    }
}