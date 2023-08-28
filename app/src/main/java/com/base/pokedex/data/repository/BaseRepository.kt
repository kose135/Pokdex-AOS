package com.base.pokedex.data.repository

import com.base.pokedex.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO, // 백그라운드 쓰레드
        call: suspend () -> Response<T>,
    ): Flow<Result<T>> = flow {

        emit(Result.Loading)

        val response = call()
        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                emit(Result.Success(data))
            } else {
                val error = response.errorBody()
                if (error != null) {
                    emit(Result.Failure(IOException(error.toString())))
                } else {
                    emit(Result.Failure(IOException("something went wrong")))
                }
            }
        } else {
            emit(Result.Failure(Throwable(response.errorBody().toString())))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Result.Failure(Exception(e)))
    }.flowOn(dispatcher)

}