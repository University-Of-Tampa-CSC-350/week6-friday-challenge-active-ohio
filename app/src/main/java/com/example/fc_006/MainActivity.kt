package com.example.fc_006

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fc_006.viewmodel.AsteroidUiState
import com.example.fc_006.viewmodel.AsteroidViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: AsteroidViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val scanButton = findViewById<Button>(R.id.scanButton)
        val statusText = findViewById<TextView>(R.id.statusText)
        val scanProgress = findViewById<ProgressBar>(R.id.scanProgress)
        val nameText = findViewById<TextView>(R.id.asteroidNameValue)
        val hazardText = findViewById<TextView>(R.id.asteroidHazardValue)
        val distanceText = findViewById<TextView>(R.id.asteroidDistanceValue)

        scanButton.setOnClickListener {
            viewModel.scanForAsteroids("BrPqQSWaM392cj4Cto4AYf0bBDuUVgLmTTnR0Ws3")
        }

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is AsteroidUiState.Idle -> {
                    scanProgress.visibility = View.GONE
                    statusText.text = getString(R.string.status_idle)
                }
                is AsteroidUiState.Loading -> {
                    scanProgress.visibility = View.VISIBLE
                    statusText.text = "Scanning Space..."
                    nameText.text = "---"
                    hazardText.text = "---"
                    distanceText.text = "---"
                }
                is AsteroidUiState.Success -> {
                    scanProgress.visibility = View.GONE
                    statusText.text = "Asteroid Detected!"
                    val asteroid = state.asteroid
                    nameText.text = asteroid.name
                    hazardText.text = if (asteroid.isPotentiallyHazardous) "POTENTIALLY HAZARDOUS" else "SAFE"
                    distanceText.text = asteroid.closeApproachData.firstOrNull()?.missDistance?.kilometers ?: "Unknown"
                }
                is AsteroidUiState.Error -> {
                    scanProgress.visibility = View.GONE
                    statusText.text = state.message
                }
            }
        }
    }
}
