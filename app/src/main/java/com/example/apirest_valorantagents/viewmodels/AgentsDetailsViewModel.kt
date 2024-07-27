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

class AgentsDetailsViewModel : ViewModel() {


    private val _uiStateDetails: MutableStateFlow<AgentDetailsUiState> = MutableStateFlow(AgentDetailsUiState.Loading2)
    val uiDetailsState: StateFlow<AgentDetailsUiState> = _uiStateDetails.asStateFlow()

    fun getDetailsAgents(agentId: String, navController: NavController) {
        viewModelScope.launch {
            try {
                Log.d("AgentsViewModel", "Iniciando a chamada da API")
                val response = OpenValorantApiDetails.retrofitService.getDetailsAgents(agentId)
                val playableAgent = response.data
                _uiStateDetails.value = AgentDetailsUiState.Error2
                Log.d("AgentsViewModel", "Agente recuperado com sucesso: $playableAgent")
            } catch (e: Exception) {
                _uiStateDetails.value = AgentDetailsUiState.Error2
                Log.e("AgentsViewModel", "Erro ao obter detalhes do agente: ${e.message}")
            }
        }
    }
}
