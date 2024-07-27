package com.example.apirest_valorantagents.ui.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.apirest_valorantagents.R

data class AppUiState(
    @StringRes val title: Int = R.string.home_title,
    val agentName: String? = null,
    val botoa : Boolean = false
)
