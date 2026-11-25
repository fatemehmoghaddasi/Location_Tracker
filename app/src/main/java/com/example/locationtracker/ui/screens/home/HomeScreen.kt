package com.example.locationtracker.ui.screens.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.locationtracker.ui.theme.BackgroundBlue
import com.example.locationtracker.ui.theme.DarkBlue
import com.example.locationtracker.ui.theme.LightBlue
import com.example.locationtracker.ui.theme.PrimaryBlue
import com.example.locationtracker.ui.theme.White

@Composable
fun HomeScreen(
    onStartTrackingClick: () -> Unit,
    permissionGranted: Boolean,
    onPermissionResultHandled: () -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val hasLocationPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            viewModel.startTracking()
            onPermissionResultHandled()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .systemBarsPadding()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            LightBlue,
                            RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Column {
                    Text(
                        text = "Location Tracker",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )
                    Text(
                        text = "Your personal location history",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    if (uiState.isTracking) Color(0xFF2E9D68)
                                    else Color.Gray,
                                    CircleShape
                                )
                        )

                        Text(
                            text = if (uiState.isTracking) "ACTIVE" else "INACTIVE",
                            fontWeight = FontWeight.Bold,
                            color = if (uiState.isTracking) Color(0xFF2E9D68)
                            else Color.Gray,
                            fontSize = 13.sp
                        )

                        Text(
                            text = "Tracking Status",
                            color = DarkBlue,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        text = if (uiState.isTracking)
                            "Tracking is active"
                        else
                            "Tracking is inactive",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )

                    Text(
                        text = if (uiState.isTracking)
                            "Your location is being tracked"
                        else
                            "Your location is not being tracked",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryBlue
                        ),
                        onClick = {
                            if (uiState.isTracking) {
                                viewModel.stopTracking()
                            } else {
                                if (hasLocationPermission) {
                                    viewModel.startTracking()
                                } else {
                                    onStartTrackingClick()
                                }
                            }
                        }
                    ) {

                        Text(
                            text = if (uiState.isTracking)
                                "Stop tracking"
                            else
                                "Start tracking",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Last Location",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )

                    uiState.currentLocation?.let { location ->

                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Latitude",
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                            Text(
                                text = location.latitude.toString(),
                                color = DarkBlue,
                                fontSize = 16.sp
                            )

                            Divider(color = LightBlue)

                            Text(
                                text = "Longitude",
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                            Text(
                                text = location.longitude.toString(),
                                color = DarkBlue,
                                fontSize = 16.sp
                            )

                            Divider(color = LightBlue)

                            Text(
                                text = "Accuracy",
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                            Text(
                                text = "${location.accuracy} meters",
                                color = DarkBlue,
                                fontSize = 16.sp
                            )
                        }

                    } ?: Text(
                        text = "No location recorded yet",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}












