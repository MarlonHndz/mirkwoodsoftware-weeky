package com.mirkwoodsoftware.data.repositories.base

import com.mirkwoodsoftware.domain.repositories.BaseRepository
import com.mirkwoodsoftware.domain.models.network.Result
import com.mirkwoodsoftware.domain.models.network.ServiceCalled
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseRepositoryImpl: BaseRepository {

    private val networkStatusFlow = MutableStateFlow<Result<Any>>(Result.Loading(ServiceCalled.EMPTY))

    suspend fun <T : Any> makeApiCallWithGlobalHandler(
        call: suspend () -> Response<T>,
        saveCallResponseLocally: suspend (T) -> Unit,
        serviceCalled: ServiceCalled,
        showLoading: Boolean = true
    ): Result<T> {
        if (showLoading) networkStatusFlow.value = Result.Loading(serviceCalled)

        // Get data from Service
        val result = makeSafeApiCall(call, serviceCalled)

        // Posting Result
        postResult(result, saveCallResponseLocally)
        return result
    }

    suspend fun <T : Any> makeSafeApiCall(
        call: suspend () -> Response<T>,
        serviceCalled: ServiceCalled,
    ): Result<T> {
        return try {
            val response = call.invoke()

            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!, serviceCalled)
            } else {
                Result.Error(IOException(response.message()), response.code(), serviceCalled)
            }
        } catch (socketTimeOutError: SocketTimeoutException) {
            Result.Error(socketTimeOutError, serviceCalled = serviceCalled)
        } catch (unknownHostError: UnknownHostException) {
            Result.Error(unknownHostError, serviceCalled = serviceCalled)
        } catch (e: Throwable) {
            Result.Error(IOException(e.message), serviceCalled = serviceCalled)
        }
    }

    private suspend fun <T : Any> postResult(
        result: Result<T>,
        saveCallResponseLocally: suspend (T) -> Unit,
    ) {
        when (result) {
            is Result.Success -> {
                saveCallResponseLocally(result.data)
                networkStatusFlow.value = result
            }
            else -> networkStatusFlow.value = result
        }
    }

    override fun getNetworkStatus():StateFlow<Result<Any>> = networkStatusFlow
}
