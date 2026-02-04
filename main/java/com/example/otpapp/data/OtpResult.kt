package com.example.otpapp.data

sealed class OtpResult {
    object Success : OtpResult()
    object Expired : OtpResult()
    object Invalid : OtpResult()
    object MaxAttemptsReached : OtpResult()
    data class Incorrect(val remainingAttempts: Int) : OtpResult()
}
