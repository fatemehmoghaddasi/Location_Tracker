package com.example.locationtracker.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.locationtracker.R

@Composable
fun LocationPermission() {

    var showDialog by remember { mutableStateOf(false) }
    var hasLocationPermission by remember { mutableStateOf<Boolean?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocation = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseLocation = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true


        if (fineLocation || coarseLocation) {
            //permission Granted
            hasLocationPermission = true
        } else {
            //permission denied
            hasLocationPermission = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp),
                painter = painterResource(
                    R.drawable.transparent_address_mobile_60e8218b76eaa8_4301763216258256754871
                ),
                contentDescription = "image"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Enable Location", fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text =
                    "Allow Location Tracker to access your device location to track and save your location history",
                fontSize = 16.sp, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Allow Location Access",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            if (hasLocationPermission == true) {
                Text(
                    text = "Location permission granted", color = Color.Green
                )
            }
            if (hasLocationPermission == false) {
                Text(
                    text = "Location permission denied", color = Color.Red
                )
            }
        }
        if (showDialog) {

            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                text = {
                    Text(
                        "Do you want to allow Location Tracker to access your location?"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false

                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("No")
                    }
                }
            )
        }
    }
}


