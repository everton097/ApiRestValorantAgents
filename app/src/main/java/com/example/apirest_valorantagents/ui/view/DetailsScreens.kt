package com.example.apirest_valorantagents.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.apirest_valorantagents.R
import com.example.apirest_valorantagents.viewmodels.AgentsDetailsViewModel
import com.example.restapi_dotahero.data.Ability
import com.example.restapi_dotahero.data.DetailsAgent


@Composable
fun DetailsScreen(
    detailsViewModel: AgentsDetailsViewModel,
) {
    val state by detailsViewModel.uiDetailsState.collectAsState()
    when (state) {
        is AgentDetailsUiState.Loading -> LoadingDetailsScreen()
        is AgentDetailsUiState.Success -> AgentDetails((state as AgentDetailsUiState.Success).agent)
        is AgentDetailsUiState.Error -> ErrorDetailsScreen()
    }
}

@Composable
fun LoadingDetailsScreen(modifier: Modifier = Modifier.fillMaxSize()) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun ErrorDetailsScreen(modifier: Modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun AgentDetails(agent: DetailsAgent) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(agent.bustPortrait)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = agent.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(10.dp, 350.dp)
                        .clip(RectangleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = agent.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Abilities", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        items(agent.abilities) { ability ->
            AbilityItem(ability = ability)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AbilityItem(ability: Ability) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
    ) {
        Column(modifier = Modifier.padding(42.dp)) {
            ability.icon?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(ability.icon)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = ability.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .widthIn(60.dp, 80.dp)
                        .heightIn(60.dp, 80.dp)
                        .clip(RectangleShape)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = ability.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = ability.description,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}