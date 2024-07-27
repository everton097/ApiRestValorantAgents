package com.example.apirest_valorantagents.ui.view

import com.example.restapi_dotahero.data.DetailsAgent

/*interface AgentDetailsUiState {
    object Loading2 : AgentDetailsUiState

    data class Success2(val agent: DetailsAgent) : AgentDetailsUiState

    object Error2 : AgentDetailsUiState

}*/

sealed class AgentDetailsUiState {
    object Loading2 : AgentDetailsUiState()
    data class Success2(val agent: DetailsAgent) : AgentDetailsUiState()
    object Error2 : AgentDetailsUiState()
}