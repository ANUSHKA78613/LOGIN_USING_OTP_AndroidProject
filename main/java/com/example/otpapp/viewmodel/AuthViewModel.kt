package com.example.otpapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.otpapp.data.OtpManager
import com.example.otpapp.data.OtpResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
class AuthViewModel : ViewModel() {
    private val otpManager = OtpManager()
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Login)
    val uiState: StateFlow<AuthUiState> = _uiState
    private var currentEmail: String = ""
    fun sendOtp(email:String) {
        currentEmail = email
        val otp = otpManager.generateOtp(email)
        Timber.i("OTP for $email is $otp")
        _uiState.value = AuthUiState.Otp(email, otp)
    }
    fun verifyOtp(otp: String,onError: (String) -> Unit) {
        when (val result = otpManager.validateOtp(currentEmail,otp)) {
            OtpResult.Success ->{
                Timber.i("OTP verified successfully")
                _uiState.value = AuthUiState.Session(
                    email = currentEmail,
                    startTime = System.currentTimeMillis()
                )
            }
            OtpResult.Expired -> onError("OTP expired")
            OtpResult.MaxAttemptsReached -> onError("Max attempts reached")
            OtpResult.Invalid -> onError("Invalid OTP")
            is OtpResult.Incorrect ->
                onError("Incorrect OTP. Attempts left: ${result.remainingAttempts}")
        }
    }
    fun logout() {
        Timber.i("User logged out")
        _uiState.value = AuthUiState.Login
    }
}
