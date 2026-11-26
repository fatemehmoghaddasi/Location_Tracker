package com.example.locationtracker.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val locations by viewModel.locations.collectAsStateWithLifecycle()
    var maplibreMap by remember { mutableStateOf<MapLibreMap?>(null) }
    var isStyleLoaded by remember { mutableStateOf(false) }

    val mapView = remember {
        MapView(context).apply {
            onCreate(null)

            getMapAsync { map ->
                maplibreMap = map

                map.setStyle(
                    "https://demotiles.maplibre.org/style.json"
                ) {
                    isStyleLoaded = true

                }
            }
        }
    }

    DisposableEffect(lifecycleOwner, mapView) {

        val observer = LifecycleEventObserver { _, event ->

            when (event) {
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            mapView.onDestroy()
        }
    }
    LaunchedEffect(maplibreMap, isStyleLoaded, locations) {

        val map = maplibreMap

        if (map != null && isStyleLoaded) {

            map.clear()

            locations.forEach { location ->

                map.addMarker(
                    MarkerOptions()
                        .position(
                            LatLng(
                                location.latitude,
                                location.longitude
                            )
                        )
                        .title("Location ${location.id}")
                        .snippet(
                            "Accuracy: ${location.accuracy} m"
                        )
                )
            }

            locations.firstOrNull()?.let { location ->
                map.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            location.latitude,
                            location.longitude
                        ),
                        14.0
                    )
                )
            }
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { mapView }
    )

}

