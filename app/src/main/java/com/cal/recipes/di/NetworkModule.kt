package com.cal.recipes.di

import com.cal.recipes.ui.screens.RecipeListViewModel
import com.cal.recipes.network.RecipeApi
import com.cal.recipes.repositories.RecipeRepository
import com.cal.recipes.repositories.RecipeRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/"

val networkModule = module {

    single<Retrofit> {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<RecipeApi> { get<Retrofit>().create(RecipeApi::class.java) }

    single<RecipeRepository> { RecipeRepositoryImpl(get<RecipeApi>()) }

    viewModel<RecipeListViewModel> { RecipeListViewModel(get<RecipeRepository>()) }
}