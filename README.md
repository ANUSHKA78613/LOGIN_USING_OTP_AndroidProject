# Passwordless Email + OTP Authentication App

## Overview
This Android application demonstrates a passwordless authentication flow using
Email + OTP, implemented using Jetpack Compose and local business logic.
No backend or network calls are used.

---

## OTP Logic and Expiry Handling
- OTP is generated locally when the user taps **Send OTP**
- OTP length: 6 digits
- OTP expiry time: 60 seconds
- Maximum allowed attempts: 3
- OTP is stored per email along with timestamp and remaining attempts
- Generating a new OTP invalidates the previous one and resets attempt count
- Expired, incorrect, and exceeded-attempt OTP cases are handled gracefully

---

## Data Structures Used and Reasoning
- `Map<String, OtpData>` is used to store OTP details per email
- This allows fast lookup, easy invalidation, and clean separation of OTP data
- `OtpData` stores OTP value, creation time, and remaining attempts
- Sealed classes (`AuthUiState`, `OtpResult`) are used for type-safe state handling

---

## External SDK Used
### Timber
Timber is used as the external SDK for logging important application events.

**Why Timber?**
- Lightweight and easy to integrate
- Does not require backend configuration
- Ideal for logging analytics-style events in assignments

**Events Logged**
- OTP generated
- OTP validation success
- OTP validation failure
- User logout

---

## AI Assistance Disclosure
A small amount of help was taken from **ChatGPT** for:
- High-level guidance
- Clarifying architectural best practices

All core logic, OTP handling, state management, UI implementation, and edge-case
handling were **designed, understood, and implemented by me independently**.

No code was blindly copied.

---

## Session Handling
- Session start time is recorded on successful login
- Live session duration is shown in `mm:ss` format
- Timer survives recomposition and screen rotation
- Timer stops correctly on logout

---

## Tech Stack
- Language: Kotlin
- UI: Jetpack Compose
- Architecture: ViewModel + UI State
- Concurrency: Kotlin Coroutines
- Logging: Timber

---

## Conclusion
This project demonstrates clean architecture, proper Compose usage,
defensive coding, and state management aligned with real-world Android
development practices.
