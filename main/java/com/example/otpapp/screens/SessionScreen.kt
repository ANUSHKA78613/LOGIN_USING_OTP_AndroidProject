package com.example.otpapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SessionScreen(
    email: String,
    startTime: Long,
    onLogout: () -> Unit
) {
    var elapsed by rememberSaveable { mutableStateOf(0L) }
    LaunchedEffect(startTime) {
        while (true) {
            elapsed = (System.currentTimeMillis() - startTime) / 1000
            delay(1000)
        }
    }
    val minutes = elapsed / 60
    val seconds = elapsed % 60
    val formatter = remember {
        SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Logged in as")
        Text(email)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Session started at")
        Text(formatter.format(Date(startTime)))
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}
