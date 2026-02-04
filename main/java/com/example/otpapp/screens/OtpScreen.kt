package com.example.otpapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    email: String,
    debugOtp: String,
    onVerifyOtp: (String, (String) -> Unit) -> Unit,
    onResendOtp: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var remainingTime by remember { mutableStateOf(60) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        remainingTime = 60
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("OTP sent to $email")
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer))
        {
            Text(
                text = "DEBUG OTP: $debugOtp",
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("OTP expires in $remainingTime seconds")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = otp,
            onValueChange = {
                otp = it
                error = null
            },
            label = { Text("Enter OTP") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        error?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
                onVerifyOtp(otp) { message ->
                    error = message
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                }
        if (otp.length == 6 && error == null) {
            Toast.makeText(context,"OTP verified successfully",Toast.LENGTH_SHORT).show()
                }
            },
            enabled = otp.length == 6 && remainingTime > 0,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color.Black,
                contentColor = androidx.compose.ui.graphics.Color.White
            )
        ) {
            Text("Verify OTP")
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextButton(
            onClick = {
                otp = ""
                error = null
                remainingTime = 60
                onResendOtp()
                Toast.makeText(context,"New OTP sent",Toast.LENGTH_SHORT).show()
            },
            enabled = remainingTime == 0
        ) {
            Text("Resend OTP")
        }
    }
}
