package com.example.apirest_valorantagents.ui.view

import com.example.restapi_dotahero.data.Agent

interface AgentsUiState {
    object Loading : AgentsUiState

    data class Success(val agents: List<Agent>) : AgentsUiState

    object Error : AgentsUiState

}