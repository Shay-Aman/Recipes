package com.cal.recipes.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cal.recipes.models.ui.RecipeListUIState
import com.cal.recipes.repositories.RecipeRepository
import com.cal.recipes.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val repository: RecipeRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(RecipeListUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            when (val response = repository.getRecipes()) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            list = response.data,
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            error = response.e
                        )
                    }
                    Log.e(RecipeListViewModel::class.simpleName, "Error: ${response.e.message} ")
                }
            }
        }
    }
}