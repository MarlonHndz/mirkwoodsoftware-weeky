package com.mirkwoodsoftware.domain.useCases

import com.mirkwoodsoftware.domain.repositories.BaseRepository
import com.mirkwoodsoftware.domain.models.network.Result
import kotlinx.coroutines.flow.StateFlow

open class NetworkStatusUseCase(
    private val baseRepository: BaseRepository
) {
    fun getNetworkStatus(): StateFlow<Result<Any>> = baseRepository.getNetworkStatus()
}
