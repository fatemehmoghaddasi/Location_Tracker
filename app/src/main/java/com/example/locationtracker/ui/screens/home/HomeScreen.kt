package com.example.locationtracker.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.locationtracker.R

@Composable
fun HomeScreen(
    onStartTrackingClick: () -> Unit,
    onStopTrackingClick: () -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "Location Tracker", fontSize = 30.sp
            )
            Image(
                modifier = Modifier
                    .size(60.dp),
                alignment = Alignment.Center,
                painter = painterResource(
                    R.drawable._5690312
                ),
                contentDescription = "image"
            )
            Text(
                text = "Tracking is inactive", fontSize = 24.sp
            )
            Text(
                text = "Your location is not being tracked"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { onStartTrackingClick() }
            ) {
                Text(
                    text = "Start tracking"
                )
            }
            Button(
                onClick = { viewModel.stopTracking() }
            ) {
                Text(
                    text = "Stop tracking"
                )
            }

        }
    }
}




