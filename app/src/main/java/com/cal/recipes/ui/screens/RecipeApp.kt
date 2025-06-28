package com.cal.recipes.ui.screens

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cal.recipes.models.responses.RecipeItem
import com.cal.recipes.utils.BiometricCipherHelper
import com.cal.recipes.utils.BiometricCipherHelper.isBiometricAvailableAndEnrolled
import com.cal.recipes.utils.authenticateWithBiometric
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
    viewModel: RecipeListViewModel = koinViewModel()
) {
    val activity = LocalActivity.current as AppCompatActivity
    val uiState by viewModel.uiState.collectAsState()
    var encryptedData by remember { mutableStateOf<Pair<ByteArray, ByteArray>?>(null) }
    var decryptedRecipe by remember { mutableStateOf<RecipeItem?>(null) }

    when {
        decryptedRecipe != null -> {
            RecipeDetailScreen(
                modifier = modifier,
                recipe = decryptedRecipe!!,
                onBack = {
                    decryptedRecipe = null
                    encryptedData = null
                }
            )
        }

        encryptedData != null -> {
            val (encryptedBytes, iv) = encryptedData!!
            LaunchedEffect(Unit) {
                val cipher = BiometricCipherHelper.getDecryptCipher(iv)
                authenticateWithBiometric(
                    activity,
                    cipher,
                    onSuccess = {
                        val decrypted = it.doFinal(encryptedBytes)
                        decryptedRecipe = Gson().fromJson(String(decrypted), RecipeItem::class.java)
                    },
                    onError = {
                        encryptedData = null
                    }
                )
            }
        }

        else -> {
            RecipeListScreen(
                modifier = modifier,
                uiState = uiState,
                onRecipeTapped = { recipe ->
                    if (isBiometricAvailableAndEnrolled(activity)) {
                        try {
                            BiometricCipherHelper.generateKeyIfNeeded()
                            val cipher = BiometricCipherHelper.getEncryptCipher()

                            authenticateWithBiometric(
                                activity = activity,
                                cipher = cipher,
                                onSuccess = {
                                    val json = Gson().toJson(recipe).toByteArray()
                                    val encrypted = it.doFinal(json)
                                    val iv = it.iv
                                    encryptedData = encrypted to iv
                                },
                                onError = {
                                    Toast.makeText(
                                        activity, "Unknown error has occurred", Toast
                                            .LENGTH_SHORT
                                    ).show()
                                }
                            )
                        } catch (e: Exception) {
                            Toast.makeText(
                                activity, "Biometric setup error: ${e.message}", Toast
                                    .LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            activity, "Biometric not available or enrolled", Toast
                                .LENGTH_LONG
                        ).show()
                    }
                }
            )
        }
    }
}

