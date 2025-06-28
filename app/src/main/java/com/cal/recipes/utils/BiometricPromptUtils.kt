package com.cal.recipes.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import javax.crypto.Cipher

fun authenticateWithBiometric(
    activity: AppCompatActivity,
    cipher: Cipher,
    onSuccess: (Cipher) -> Unit,
    onError: () -> Unit
) {
    val prompt = BiometricPrompt(
        activity,
        ContextCompat.getMainExecutor(activity),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                result.cryptoObject?.cipher?.let(onSuccess)
            }

            override fun onAuthenticationError(code: Int, msg: CharSequence) {
                onError()
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Authentication required")
        .setSubtitle("Please verify your identity")
        .setNegativeButtonText("Cancel")
        .build()

    prompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
}
