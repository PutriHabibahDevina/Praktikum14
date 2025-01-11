package com.example.praktikum14.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meet15.Viewmodel.HomeUiState
import com.example.praktikum14.ui.viewmodel.FormState
import com.example.praktikum14.ui.viewmodel.InsertUiState
import com.example.praktikum14.ui.viewmodel.InsertViewModel
import com.example.praktikum14.ui.viewmodel.MahasiswaEvent
import com.example.praktikum14.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InsertMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState //State utama untuk loading, success, dan error
    val uiEvent = viewModel.uiEvent //State untuk form dan validasi
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan state untuk snackbar dan navigasi
    LaunchedEffect (uiState) {
        when (uiState) {
            is FormState.Success -> {
                println(
                    "InsertMhsView: uiState is FormState.Success, navigate to home " + uiState.messege
                )
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(uiState.messege) //Tampilkan snackbar
                }
                delay(700)
                //Navigasi langsung
                onNavigate()

                viewModel.resetSnackBarMessage() //Reset snackbar state
            }

            is FormState.Error -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(uiState.messege)
                }
            }

            else -> Unit
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {Text("Tambah Mahasiswa")},
                navigationIcon = {
                    Button(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyMhs (
                uiState = uiEvent,
                homeUiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    if(viewModel.validateFields()) {
                        viewModel.insertMhs()
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodyMhs (
    modifier: Modifier = Modifier,
    onValueChange: (MahasiswaEvent) -> Unit,
    uiState: InsertUiState,
    onClick: () -> Unit,
    homeUiState: FormState
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMahasiswa (
            mahasiswaEvent = uiState.insertUiEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = homeUiState !is FormState.Loading
        ) {
            if(homeUiState is FormState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp)
                )
                Text("Loading...")
            } else {
                Text("Add")
            }
        }
    }
}