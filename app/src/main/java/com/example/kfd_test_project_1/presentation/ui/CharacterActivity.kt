package com.example.kfd_test_project_1.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kfd_test_project_1.domain.entity.CharacterEntity
import com.example.kfd_test_project_1.presentation.viewmodel.CharacterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = koinViewModel()
) {
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadCharacters()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Персонажи") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.loadCharacters(true)
                }
            }) {
                Icon(Icons.Default.Refresh, contentDescription = "Обновить")
            }
        }
    ) {
        padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                isLoading -> {
                    Text(
                        text = "Загрузка данных. Подождите...",
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                }

                isError -> {
                    Text(
                        text = "Ошибка загрузки данных.",
                        color = Color.Red,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                }

                else -> {
                    LazyColumn {
                        items(characters) { character ->
                            CharacterView(character)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterView(character: CharacterEntity) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = "Картинка ${character.name}",
                modifier = Modifier
                    .size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Name: ${character.name}",
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                Text(
                    text = "Status: ${character.status}",
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                Text(
                    text = "Species: ${character.species}",
                    fontSize = 20.sp,
                    modifier = Modifier
                )
            }
        }
    }
}