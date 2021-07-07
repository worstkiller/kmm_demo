package com.vikas.kotlinmultiplatformdemo.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.vikas.kotlinmultiplatformdemo.models.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var kmmDemoViewModel: KmmDemoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kmmDemoViewModel = defaultViewModelProviderFactory.create(KmmDemoViewModel::class.java)
        setContent {
            getCurrentScreen(kmmDemoViewModel)
        }
    }
}

@Composable
fun getCurrentScreen(kmmDemoViewModel: KmmDemoViewModel) {
    var screen by remember { mutableStateOf(Navigation.SPLASH) }
    val doggoList by kmmDemoViewModel.doggoList.observeAsState()
    val apiStatus by kmmDemoViewModel.baseViewState.observeAsState()

    when (screen) {
        Navigation.HOME -> {
            getHomeView(baseViewState = apiStatus, doggoList ?: listOf())
        }
        Navigation.SPLASH -> {
            getSplashScreen {
                screen = Navigation.HOME
            }
        }
    }
}

@Composable
fun getSplashScreen(splashTimeEnd: () -> Unit) {
    val composableScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF311B92)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.paw_print),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                Color.White
            )
        )
        composableScope.launch {
            delay(3000)
            splashTimeEnd()
        }

    }
}
