package com.example.apirest_valorantagents.ui.view

import com.example.restapi_dotahero.data.DetailsAgent

interface AgentDetailsUiState {
    object Loading : AgentDetailsUiState

    data class Success(val agent: DetailsAgent) : AgentDetailsUiState

    object Error : AgentDetailsUiState

}