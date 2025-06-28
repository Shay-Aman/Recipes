package com.cal.recipes.utils

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val e: Throwable): Result<T>()
}