package com.example.testcities.util

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<ConnectStatus>

    enum class ConnectStatus{
        AVAILABLE, LOST
    }
}