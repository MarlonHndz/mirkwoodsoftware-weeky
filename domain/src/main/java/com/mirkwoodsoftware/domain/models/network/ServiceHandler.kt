package com.mirkwoodsoftware.domain.models.network

data class ServiceHandler(
    val status: ServiceStatus,
    val serviceCalled: ServiceCalled
)

enum class ServiceStatus {
    LOADING,
    SUCCESS,
    ERROR_SERVICE,
    ERROR_TIMEOUT,
    ERROR_UNKNOWN_HOST,
    ERROR_NO_INTERNET_CONNECTION
}

enum class ServiceCalled {
    EMPTY,
    MEDICINE_GET,
}
