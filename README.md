

---

# MusicApp

This Android app uses the Deezer API to fetch and display music-related data such as artists, albums, and popular tracks. The app showcases a simple user interface with horizontal scrollable lists for each category. It integrates Retrofit for API calls and dynamically updates the UI based on the data fetched.

## Features

- **Artist List**: Displays a horizontal list of unique artists.
- **Album List**: Shows a horizontal list of albums.
- **Popular Tracks**: Lists tracks that are less popular (based on a rank threshold).
- **Search Bar**: Users can search for artists to fetch and display related music data.
- **Edge-to-Edge UI**: The app includes modern UI design elements like edge-to-edge views.


## Requirements

- Android Studio
- Minimum SDK: 21 (Lollipop)
- Retrofit Library
- Gson Converter

## Libraries Used

- **Retrofit**: Used for making network requests and handling responses from the Deezer API.
- **Gson**: For converting JSON responses into Kotlin objects.
- **AndroidX**: For UI components and backward compatibility.
- **ViewCompat**: To handle window insets and ensure edge-to-edge layouts.

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/musicapp.git
   cd musicapp
   ```

2. **Open the project in Android Studio**.

3. **Add your API keys**:
   To use the Deezer API, you need to get an API key from [RapidAPI](https://rapidapi.com/deezerdevs/api/deezer). 
   Replace the base URL and API key in your Retrofit configuration.

4. **Build and Run the App**:
   Connect an Android device or use an emulator. Then, click on "Run" in Android Studio.

## File Structure

- `MainActivity2.kt`: The main activity that sets up the user interface and makes API calls.
- `activity_main2.xml`: Layout for the main activity.
- `MyAdapter.kt`, `MyAdapter2.kt`, `MyAdapter3.kt`: Custom adapters for displaying artist, album, and popular tracks respectively.
- `apiInterface.kt`: Retrofit interface for defining the API endpoints.

## Usage

- The app will fetch music data related to the search term "eminem" from the Deezer API.
- Users can interact with the UI to explore different music categories (artists, albums, tracks).
- Tap on the search bar to navigate to another activity (`MainActivity4`).

