package com.example.otpapp.data
data class OtpData(
    val otp: String,
    val createdAt: Long,
    val attemptsLeft: Int)
