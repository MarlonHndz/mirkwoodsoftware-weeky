package com.mirkwoodsoftware.presentation.ui.networkHandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkwoodsoftware.domain.models.network.Result
import com.mirkwoodsoftware.domain.models.network.ServiceCalled
import com.mirkwoodsoftware.domain.models.network.ServiceHandler
import com.mirkwoodsoftware.domain.models.network.ServiceStatus
import com.mirkwoodsoftware.domain.useCases.NetworkStatusUseCase
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkStatusViewModel(
    private val networkStatusUseCase: NetworkStatusUseCase
) : ViewModel() {

    private val _networkStatusHandler = MutableLiveData<ServiceHandler>()
    val networkStatusHandlerLiveData: LiveData<ServiceHandler>
        get() = _networkStatusHandler

    init {
        getNetworkStatus()
    }

    private fun getNetworkStatus() {
        viewModelScope.launch {
            networkStatusUseCase.getNetworkStatus().collect() { result ->
                _networkStatusHandler.value = when (result) {
                    is Result.Loading -> ServiceHandler(ServiceStatus.LOADING, result.serviceCalled)
                    is Result.Success -> ServiceHandler(ServiceStatus.SUCCESS, result.serviceCalled)
                    is Result.Error -> getErrorCode(result, result.serviceCalled)
                    else -> ServiceHandler(
                        ServiceStatus.ERROR_NO_INTERNET_CONNECTION,
                        ServiceCalled.EMPTY
                    )
                }
            }
        }
    }

    private fun getErrorCode(
        error: Result.Error,
        serviceCalled: ServiceCalled
    ): ServiceHandler {
        return when (error.exception) {
            is SocketTimeoutException -> {
                ServiceHandler(ServiceStatus.ERROR_TIMEOUT, serviceCalled)
            }

            is UnknownHostException -> {
                ServiceHandler(ServiceStatus.ERROR_UNKNOWN_HOST, serviceCalled)
            }

            else -> ServiceHandler(ServiceStatus.ERROR_SERVICE, serviceCalled)
        }
    }
}
