# Recipe App

This is a single-page recipe browsing app built with **Jetpack Compose**, featuring:
- HTTP-based data loading
- Material 3 UI
- Error handling
- Biometric-based encryption and decryption of recipe details

---

## 📦 Features

- ✅ Loads recipes from a remote JSON endpoint
- ✅ Displays a list with name, thumb, fats, calories, and carbos
- ✅ Taps on a recipe trigger **biometric encryption + decryption**
- ✅ Full recipe detail shown only after successful biometric authentication
- ✅ Handles missing biometric support gracefully
- ✅ Uses `ViewModel` + `StateFlow` + `Koin` for scalable architecture

---

## 🔐 Security Approach

- Uses **Android Keystore** + **BiometricPrompt** for AES encryption
- Recipes are **encrypted after selection** and **decrypted** only after biometric authentication
- Fallback logic prevents crashes on devices with missing or unenrolled biometrics

---

## 🔧 Tech Stack

- Kotlin + Jetpack Compose
- Material 3
- Koin (DI)
- Coil (image loading)
- BiometricPrompt API
- Android Keystore
- Gson (for encryption serialization)

---

## 🚀 Setup

1. Clone the repo
2. Ensure your device has enrolled biometrics
3. Run on Android 6.0+ (API 23+)

---

## ⚠️ Notes

- If no biometrics are available/enrolled, the app skips encryption/decryption and shows a warning
- Does not persist encrypted data (only held in memory for simplicity)

---

## 📄 License

This project is for demo/educational use. You may adapt it to suit your app needs.

