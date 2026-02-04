package com.example.otpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.example.otpapp.screens.LoginScreen
import com.example.otpapp.screens.OtpScreen
import com.example.otpapp.screens.SessionScreen
import com.example.otpapp.ui.theme.OtpAppTheme
import com.example.otpapp.viewmodel.AuthUiState
import com.example.otpapp.viewmodel.AuthViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContent {
    OtpAppTheme {
    val viewModel: AuthViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        AuthUiState.Login -> {
            LoginScreen { email ->
                viewModel.sendOtp(email)
            }
        }
        is AuthUiState.Otp -> {
            OtpScreen(
                email = uiState.email,
                debugOtp = uiState.debugOtp,
                onVerifyOtp = { otp, onError ->
                    viewModel.verifyOtp(otp, onError)
                },
                onResendOtp = {
                    viewModel.sendOtp(uiState.email)
                }
            )
        }
        is AuthUiState.Session -> {
            SessionScreen(
                email = uiState.email,
                startTime = uiState.startTime,
                onLogout = {
                    viewModel.logout()
                })
        }
    }
    }
  }
 }
}
