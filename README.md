# 📍 Location Tracker

A modern Android application that tracks and records the user's location every 30 minutes using background location tracking. Built with **Kotlin** and **Jetpack Compose**, the app allows users to start location tracking, view their current location on a map, and access their location history.

The project focuses on modern Android development practices, background execution, location services, and local data persistence.

## ✨ Features

- **Location Tracking:** Automatically captures the user's location every 30 minutes.
- **Background Tracking:** Continues location tracking through a foreground service when the app is in the background or the screen is off.
- **Live Location:** Displays the user's location on an interactive map.
- **Location History:** Saves previously recorded locations for later viewing.
- **Permission Handling:** Requests and manages location and notification permissions.
- **Foreground Service:** Keeps the tracking process running with a persistent notification.
- **Local Storage:** Stores location records using Room Database.
- **Modern UI:** Built with Jetpack Compose and Material 3.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Programming language |
| Jetpack Compose | Declarative UI |
| Material 3 | UI components |
| MVVM | Application architecture |
| Repository Pattern | Data management |
| Hilt | Dependency injection |
| Room Database | Local data persistence |
| Kotlin Coroutines | Asynchronous programming |
| StateFlow | Reactive state management |
| Google Play Services Location | Location updates |
| Foreground Service | Background location tracking |
| MapLibre | Interactive maps |
| Navigation Compose | Screen navigation |
| Kotlinx Serialization | Serialization |
| Lifecycle | Lifecycle-aware state collection |

## 🏗️ Architecture

The application follows the **MVVM architecture** combined with the Repository Pattern to maintain a clean separation of concerns.

Main architectural components:

- **UI Layer:** Jetpack Compose screens for user interaction and displaying location information.
- **ViewModel Layer:** Manages UI state and communicates with repositories.
- **Repository Layer:** Handles location data operations and provides data to ViewModels.
- **Data Layer:** Uses Room Database for storing location history.
- **Service Layer:** Manages background location tracking through a foreground service.
- **Dependency Injection:** Uses Hilt to provide and manage dependencies.

## 📱 Screens

The application includes the following screens:

- **Permission Screen:** Explains the required permissions and requests access to device location.
- **Home Screen:** Allows users to start and manage location tracking.
- **Map Screen:** Displays the user's location and recorded locations on a map.
- **History Screen:** Provides access to previously recorded location data.

## 🔐 Permissions

The application requires the following Android permissions:

- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`
- `FOREGROUND_SERVICE`
- `FOREGROUND_SERVICE_LOCATION`
- `POST_NOTIFICATIONS`
- `INTERNET`

Location permissions are required to access device location, while foreground service and notification permissions support ongoing tracking.

## 🚀 Getting Started

### Prerequisites

- Android Studio
- JDK 11 or compatible version
- Android SDK
- Android device or emulator running Android 8.0 (API 26) or higher

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/fatemehmoghaddasi/Location_Tracker.git
   ```

2. Open the project in Android Studio.

3. Wait for Gradle synchronization to complete.

4. Run the application on an Android device or emulator.

5. Grant the required permissions and start location tracking.

**Note:** For reliable background location tracking, use a physical Android device and ensure the required permissions are granted.

## 📸 Screenshots

*Screenshots will be added here.*

<!-- Add application screenshots here -->

## 🎯 Learning Objectives

This project provided practical experience with:

- Implementing background location tracking in Android.
- Working with Android Foreground Services.
- Handling runtime permissions.
- Integrating Google Play Services Location.
- Displaying location data on interactive maps.
- Managing local data with Room Database.
- Applying MVVM and Repository Pattern.
- Using Hilt for dependency injection.
- Managing reactive UI state with StateFlow and Coroutines.

## 👩‍💻 Developer

**Fatemeh Moghaddasi**

- GitHub: [@fatemehmoghaddasi](https://github.com/fatemehmoghaddasi)

---

**Built with Kotlin and Jetpack Compose ❤️**
