package com.example.imageslidewithindicator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imageslidewithindicator.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@ExperimentalPagerApi
@Composable
fun MovieScreen(
    viewModel: MainViewModel
) {
    viewModel.getAllMovies()

    val isLoading = remember { mutableStateOf(true) }

    val state = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SliderView(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.padding(4.dp))
        DotsIndicator(
            totalDots = viewModel.movieListResponse.size,
            selectedIndex = state.currentPage
        )
        LazyColumn {
            items(viewModel.movieListResponse) { item ->
                isLoading.value = !isLoading.value
                MovieCard(movie = item)
            }
        }
        if (isLoading.value) {
            CircularProgressIndicator()
        }
    }
    LaunchedEffect(key1 = state.currentPage) {
        delay(3000)
        var newPosition = state.currentPage + 1
        if (newPosition > viewModel.movieListResponse.size - 1) newPosition = 0
        state.animateScrollToPage(newPosition)
    }
}