package com.example.apirest_valorantagents.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.network.HttpException
import com.example.apirest_valorantagents.R
import com.example.apirest_valorantagents.ui.view.AgentDetailsUiState
import com.example.apirest_valorantagents.ui.view.AgentsUiState
import com.example.apirest_valorantagents.ui.view.AppScreens
import com.example.apirest_valorantagents.ui.view.AppUiState
import com.example.restapi_dotahero.data.Agent
import com.example.restapi_dotahero.network.OpenValorantApi
import com.example.restapi_dotahero.network.OpenValorantApiDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class AgentsViewModel : ViewModel() {

    private var _uiState: MutableStateFlow<AgentsUiState> = MutableStateFlow(AgentsUiState.Loading)
    val uiState: StateFlow<AgentsUiState> = _uiState.asStateFlow()

    init {
        getAgents()
    }

    private fun getAgents() {
        viewModelScope.launch {
            try {
                val apiResponse = OpenValorantApi.retrofitService.getAgents()
                val playableAgents = apiResponse.data.filter { it.isPlayableCharacter }
                _uiState.value = AgentsUiState.Success(playableAgents)
                Log.d("AgentsViewModel", "Success1: ${playableAgents}")
            } catch (e: IOException) {
                _uiState.value = AgentsUiState.Error
                Log.e("AgentsViewModel", "Failure IOException: ${e.message}")
            } catch (e: HttpException) {
                _uiState.value = AgentsUiState.Error
                val apiResponse = e.response.toString()
                Log.e("AgentsViewModel", "Failure HttpException: ${e.message}")
                Log.e("AgentsViewModel", "API response: $apiResponse")
            } catch (e: Exception) {
                _uiState.value = AgentsUiState.Error
                Log.e("AgentsViewModel", "An unexpected error occurred: ${e.message}")
            }
        }
    }

}
