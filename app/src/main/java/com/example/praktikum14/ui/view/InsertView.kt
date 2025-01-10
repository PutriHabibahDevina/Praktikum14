package com.example.praktikum14.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum14.ui.viewmodel.FormState
import com.example.praktikum14.ui.viewmodel.InsertViewModel
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
}