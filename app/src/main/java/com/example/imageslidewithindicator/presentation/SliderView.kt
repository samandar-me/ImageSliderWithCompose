package com.example.imageslidewithindicator.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.imageslidewithindicator.R
import com.example.imageslidewithindicator.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun SliderView(
    state: PagerState,
    viewModel: MainViewModel
) {
    val imageUrl = remember { mutableStateOf("") }
    HorizontalPager(
        state = state,
        count = viewModel.movieListResponse.size,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) { page ->
        val movie = viewModel.movieListResponse[page]
        imageUrl.value = movie.imageUrl

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl.value)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.placeholder_bg)
                                .scale(Scale.FILL)
                        }).build()
                )
                Image(
                    painter = painter, contentDescription = "image",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(
                            RoundedCornerShape(10.dp),
                        )
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = movie.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp)
                        .background(Color.LightGray.copy(alpha = 0.60F))
                        .padding(8.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}