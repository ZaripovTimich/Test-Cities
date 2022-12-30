package com.example.testcities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.testcities.databinding.ActivityMainBinding
import com.example.testcities.util.ConnectStatus
import com.example.testcities.util.ConnectivityObserver
import com.example.testcities.util.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

private const val DEFAULT_DELAY = 1500L

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            connectivityObserver.observe().collect { status ->
                withContext(Dispatchers.Main) {
                    when (status) {
                        ConnectStatus.AVAILABLE -> {
                            binding.internetConnection.setBackgroundColor(
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.green
                                )
                            )
                            binding.tvNetwork.text = getText(R.string.connected)
                            delay(DEFAULT_DELAY)
                            binding.internetConnection.visibility = View.GONE
                        }
                        ConnectStatus.LOST -> {
                            binding.internetConnection.visibility = View.VISIBLE
                            binding.internetConnection.setBackgroundColor(
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.red
                                )
                            )
                            binding.tvNetwork.text = getText(R.string.no_internet_connection)
                        }
                    }
                }
            }
        }
    }
}