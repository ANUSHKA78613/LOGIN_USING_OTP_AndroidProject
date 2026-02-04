package com.example.otpapp.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onSendOtp: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login")
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
        when {
            email.isBlank() -> {
                Toast.makeText(
                    context,
                    "Email cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
        Toast.makeText(context,"Please enter a valid email",Toast.LENGTH_SHORT).show()
        }
        else -> {
            Toast.makeText(context,"OTP sent successfully",Toast.LENGTH_SHORT).show()
            onSendOtp(email)
        }
    }
},
    modifier = Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(
    containerColor = androidx.compose.ui.graphics.Color.Black,
    contentColor = androidx.compose.ui.graphics.Color.White)) {
    Text("Send OTP")
}
}
}
