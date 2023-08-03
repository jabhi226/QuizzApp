package com.example.quizzapp.di

import com.example.quizzapp.modules.core.constants.Endpoints
import com.example.quizzapp.modules.quizModule.repository.QuizRepository
import com.example.quizzapp.modules.quizModule.repository.QuizRepositoryImpl
import com.example.quizzapp.modules.quizModule.network.QuizApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .baseUrl(Endpoints.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): QuizApiService =
        retrofit.create(QuizApiService::class.java)

    @Provides
    @Singleton
    fun provideQuizRepository(quizApiService: QuizApiService): QuizRepository {
        return QuizRepositoryImpl(quizApiService)
    }

}