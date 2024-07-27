package com.example.apirest_valorantagents.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.apirest_valorantagents.R
import com.example.apirest_valorantagents.viewmodels.AgentsViewModel
import com.example.restapi_dotahero.data.Ability
import com.example.restapi_dotahero.data.Agent
import com.example.restapi_dotahero.data.DetailsAgent
import com.example.restapi_dotahero.network.BASE_URL


@Composable
fun DetailsScreen(
    agentsViewModel: AgentsViewModel = viewModel(),
    navController: NavController,
) {

    val uiDetailsState by agentsViewModel.uiDetailsState.collectAsState()
    Log.d("AgentsViewModel", "_uiStateDetails.value3: ${agentsViewModel.uiDetailsState.collectAsState()}")
    when (uiDetailsState) {
        is AgentDetailsUiState.Loading -> LoadingDetailsScreen()
        is AgentDetailsUiState.Success -> AgentDetails((uiDetailsState as AgentDetailsUiState.Success).agent)
        is AgentDetailsUiState.Error -> ErrorDetailsScreen()
    }
}
@Composable
fun LoadingDetailsScreen(modifier: Modifier = Modifier.fillMaxSize()) {
    Image(
        painter = painterResource(
            id = R.drawable.loading_img
        ),
        contentDescription = "",
        modifier = modifier.size(200.dp),
    )
    Log.d("AgentsViewModel", "Estado em loading")
}

@Composable
fun ErrorDetailsScreen(modifier: Modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun AgentDetails(
    agent: Agent,
) {
    Log.d("AgentsViewModel", "agent11: ${agent}")
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(0F) }
    val height = remember { mutableStateOf(0F) }

    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(agent.img)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = agent.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(10.dp, 150.dp)
                .clip(RectangleShape)
                .onGloballyPositioned {
                    width.value = it.size.width / density
                    height.value = it.size.height / density
                }
        )
        Box(
            modifier = Modifier
                .size(
                    width = width.value.dp,
                    height = height.value.dp,
                )
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black),
                        startY = 100F,
                        endY = 1000F,
                    )
                )
        )
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = agent.name,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

fun getSampleAgent(): DetailsAgent {
    return DetailsAgent(
        id = "320b2a48-4d9b-a075-30f1-1f93a9b638fa",
        name = "Sova",
        img = "https://media.valorant-api.com/agents/320b2a48-4d9b-a075-30f1-1f93a9b638fa/displayicon.png",
        description = "Born from the eternal winter of Russia's tundra, Sova tracks, finds, and eliminates enemies with ruthless efficiency and precision. His custom bow and incredible scouting abilities ensure that even if you run, you cannot hide.",
        bustPortrait = "https://media.valorant-api.com/agents/320b2a48-4d9b-a075-30f1-1f93a9b638fa/fullportrait.png",
        isPlayableCharacter = true,
        abilities = listOf(
            Ability(
                slot = "Ability1",
                name = "Shock Bolt",
                description = "EQUIP a bow with a shock bolt. FIRE to send the explosive bolt forward, detonating upon collision and damaging players nearby. HOLD FIRE to extend the range of the projectile. ALT FIRE to add up to two bounces to this arrow.",
                icon = "https://media.valorant-api.com/agents/320b2a48-4d9b-a075-30f1-1f93a9b638fa/abilities/ability1/displayicon.png"
            ),
            Ability(
                slot = "Ability2",
                name = "Recon Bolt",
                description = "EQUIP a bow with recon bolt. FIRE to send the recon bolt forward, activating upon collision and Revealing the location of nearby enemies caught in the line of sight of the bolt. Enemies can destroy this bolt. HOLD FIRE to extend the range of the projectile. ALT FIRE to add up to two bounces to this arrow.",
                icon = "https://media.valorant-api.com/agents/320b2a48-4d9b-a075-30f1-1f93a9b638fa/abilities/ability2/displayicon.png"
            )
        )
    )
}
@Composable
fun AgentDetails(agent: DetailsAgent) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(agent.img)
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
        Text(text = agent.name, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = agent.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Abilities", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        agent.abilities.forEach { ability ->
            AbilityItem(ability = ability)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AbilityItem(ability: Ability) {
    Column {
        Text(text = ability.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = ability.description, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        ability.icon?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = ability.name,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAgentDetails() {
    AgentDetails(agent = getSampleAgent())
}


