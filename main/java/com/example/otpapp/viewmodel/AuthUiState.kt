package com.example.otpapp.viewmodel

sealed class AuthUiState {
    object Login : AuthUiState()
    data class Otp(val email: String, val debugOtp: String) : AuthUiState()
    data class Session(val email: String, val startTime: Long) : AuthUiState()
}
