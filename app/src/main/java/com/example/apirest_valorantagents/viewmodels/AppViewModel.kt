package com.example.apirest_valorantagents.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.apirest_valorantagents.R
import com.example.apirest_valorantagents.ui.view.AppScreens
import com.example.apirest_valorantagents.ui.view.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _appUiState: MutableStateFlow<AppUiState> =
        MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> =
        _appUiState.asStateFlow()


    fun navigateToDetails(navController: NavController, agentName: String) {
        _appUiState.update { currentState ->
            currentState.copy(
                title = R.string.details_title,
                agentName = agentName,
                botoa = true
            )
        }
        navController.navigate(AppScreens.details.name)
    }

    fun navigateToHome(navController: NavController) {
        _appUiState.update { currentState ->
            currentState.copy(
                title = R.string.home_title,
                agentName = null,
                botoa = false
            )
        }
        navController.navigate(AppScreens.home.name) {
            popUpTo(AppScreens.home.name) {
                inclusive = true
            }
        }
    }

}