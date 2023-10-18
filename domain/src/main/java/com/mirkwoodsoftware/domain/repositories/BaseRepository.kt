package com.mirkwoodsoftware.domain.repositories

import com.mirkwoodsoftware.domain.models.network.Result
import kotlinx.coroutines.flow.StateFlow

interface BaseRepository {
    fun getNetworkStatus(): StateFlow<Result<Any>>
}
