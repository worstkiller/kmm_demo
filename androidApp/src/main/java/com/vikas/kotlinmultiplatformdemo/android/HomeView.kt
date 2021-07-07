package com.vikas.kotlinmultiplatformdemo.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.vikas.kotlinmultiplatformdemo.models.BaseViewState
import com.vikas.kotlinmultiplatformdemo.models.DoggoImage
import com.vikas.kotlinmultiplatformdemo.models.DoggoResponseModel

@Composable
fun getHomeView(baseViewState: BaseViewState?, doggoResponseModels: List<DoggoResponseModel>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (baseViewState) {
            is BaseViewState.LOADING -> {
                CircularProgressIndicator()
            }
            is BaseViewState.ERROR -> {
                Text(text = "${baseViewState.message}: ${baseViewState.errorCode}")
            }
            is BaseViewState.SUCCESS -> {
                LazyColumn {
                    items(doggoResponseModels) {
                        getSingleDogView(it)
                    }
                }
            }
        }
    }
}

@Composable
fun getSingleDogView(
    doggoBreedResponseModel: DoggoResponseModel
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        backgroundColor = Color(0xFFF8F8F8)
    ) {

        Row(
            modifier = Modifier
                .background(shape = RoundedCornerShape(20.dp), color = Color.White)
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Image(
                painter = rememberCoilPainter(request = doggoBreedResponseModel.image.url),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth(.30f)
                    .height(120.dp),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Text(
                    text = doggoBreedResponseModel.name,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(.80f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "Breed: ${doggoBreedResponseModel.breed_group ?: "NA"}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
                    color = Color.Gray.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                )

                Text(
                    text = "Life span: ${doggoBreedResponseModel.life_span}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
                    color = Color.Gray.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                )

                Text(
                    text = "Origin: ${doggoBreedResponseModel.origin ?: doggoBreedResponseModel.country_code ?: "NA"}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
                    color = Color.Gray.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

        }

    }
}

@Composable
@Preview
fun Preview_HomeView() {
    getSingleDogView(
        doggoBreedResponseModel = DoggoResponseModel(
            "hello",
            DoggoImage(""),
            "12",
            "Pitbull",
            "peru",
            "angry"
        )
    )
}
