package com.example.locationtracker.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.locationtracker.ui.theme.BackgroundBlue
import com.example.locationtracker.ui.theme.Brown
import com.example.locationtracker.ui.theme.DarkBlue
import com.example.locationtracker.ui.theme.LightBlue
import com.example.locationtracker.ui.theme.PrimaryBlue
import com.example.locationtracker.ui.theme.White
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(onNavigateNext: () -> Unit = {}) {
    var visible by remember { mutableStateOf(false) }

    // انیمیشن ورود لوگو
    val logoScale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.4f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "logoScale"
    )

    // پالس پین
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseScale"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseAlpha"
    )

    // چرخش حلقه مدار
    val ringRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringRotation"
    )

    LaunchedEffect(Unit) {
        visible = true
        delay(2400.milliseconds)
        onNavigateNext()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        BackgroundBlue,
                        LightBlue.copy(alpha = 0.7f),
                        BackgroundBlue
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        // 🌐 مدار خط‌چین چرخان
        Box(
            modifier = Modifier
                .size(360.dp)
                .rotate(ringRotation)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = PrimaryBlue.copy(alpha = 0.15f),
                    radius = size.minDimension / 2,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(20f, 15f), 0f
                        )
                    )
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue.copy(alpha = 0.5f))
            )
        }

        // حلقه میانی نرم
        Box(
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            LightBlue.copy(alpha = 0.5f),
                            Color.Transparent
                        )
                    )
                )
        )

        // محتوای اصلی
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Box(
                modifier = Modifier.size(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .scale(pulseScale)
                        .alpha(pulseAlpha)
                        .clip(CircleShape)
                        .background(PrimaryBlue.copy(alpha = 0.3f))
                )

                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .scale(logoScale)
                        .alpha(if (visible) 1f else 0f)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(PrimaryBlue, DarkBlue)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = White,
                        modifier = Modifier.size(70.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Location Tracker",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkBlue,
                modifier = Modifier.alpha(if (visible) 1f else 0f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "your location, always in reach",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Brown,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(if (visible) 1f else 0f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(PrimaryBlue, LightBlue)
                        )
                    )
            )

        }

    }
}


