package com.example.otpapp.data
import kotlin.random.Random
class OtpManager {
    private val otpStore = mutableMapOf<String, OtpData>()
    private val OTP_EXPIRY_MS = 60_000L
    private val MAX_ATTEMPTS = 3
    fun generateOtp(email: String): String {
        val otp = Random.nextInt(100000, 999999).toString()
        otpStore[email] = OtpData(otp, System.currentTimeMillis(), MAX_ATTEMPTS)
        return otp
    }
    fun validateOtp(email: String, enteredOtp: String): OtpResult {
        val data = otpStore[email] ?: return OtpResult.Invalid
        if (System.currentTimeMillis() - data.createdAt > OTP_EXPIRY_MS) {
            otpStore.remove(email)
            return OtpResult.Expired
        }
        if (data.attemptsLeft <= 0) {
            otpStore.remove(email)
            return OtpResult.MaxAttemptsReached
        }
        return if (data.otp == enteredOtp) {
            otpStore.remove(email)
            OtpResult.Success
        } else {
            otpStore[email] =
                data.copy(attemptsLeft = data.attemptsLeft - 1)
            OtpResult.Incorrect(data.attemptsLeft - 1)
        }
    }
}
