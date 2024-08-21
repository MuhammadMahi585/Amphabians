package com.example.amphabians.ui.theme


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphabians.R
import com.example.amphabians.Screen.AmphibiansViewModel
import com.example.amphabians.Screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Amphibians",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
            HomeScreen(
                amphibiansUIState = amphibiansViewModel.amphibiansUIState,
                retryAction = amphibiansViewModel::getAmphibians,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}