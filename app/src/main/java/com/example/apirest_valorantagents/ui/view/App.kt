package com.example.apirest_valorantagents.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apirest_valorantagents.viewmodels.AgentsViewModel
import com.example.apirest_valorantagents.viewmodels.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier
) {
    val appViewModel: AppViewModel = viewModel()
    val agentsViewModel: AgentsViewModel = viewModel()
    val navController = rememberNavController()

    val uiState by appViewModel.appUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                //Text(text = if (uiState.agentName != null) uiState.agentName else stringResource(id = uiState.title))
                (if (uiState.agentName != null) uiState.agentName else stringResource(id = uiState.title))?.let { Text(text = it) }
            })
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.home.name,
            modifier = modifier.padding(it)
        ) {
            composable(route = AppScreens.home.name) {
                AgentsScreen(navController = navController, appViewModel = appViewModel)
            }
            composable(route = AppScreens.details.name) {
                DetailsScreen(
                    agentsViewModel = agentsViewModel,
                    navController = navController,
                )
            }
        }
    }
}

enum class AppScreens {
    home,
    details,
}