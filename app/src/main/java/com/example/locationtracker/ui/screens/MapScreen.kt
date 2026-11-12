package com.example.locationtracker.ui.screens

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MapScreen(){
    Text(
        "Map", modifier = Modifier.systemBarsPadding(), fontSize = 30.sp
    )
}