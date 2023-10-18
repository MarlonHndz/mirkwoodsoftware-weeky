package com.mirkwoodsoftware.domain.repositories

import com.mirkwoodsoftware.domain.models.Medicine
import com.mirkwoodsoftware.domain.models.network.Result
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

    /**
     * This method "getMedicines()" gives you the data from the Database after it ask for an update to the service.
     * It means that you will always get the data from the DB, but the method tries to update
     * the DB with the server info before to emit the data. In addition, it allows you to
     * handle the network status with the variable "networkStatusFlow". It means that you could
     * handle the network status (Error/Loading/Success) in one place across the app with
     * a BaseViewModel and BaseFragment for example.
     */
    suspend fun getMedicines(): Flow<List<Medicine>>

    /**
     * This method "getMedicinesWithNetworkStatus()" allows you to get the network state and the data in the same response.
     * It means, you can handle the Loading, Error and Success status
     * within the response of this method. In other words, in the Activity or Fragment you could
     * have the opportunity to ask if the Result is (Error/Loading/Success) and do whatever you want
     * on each status. Also you have an emission for the current local items before you get the
     * service response so you get data to show in case it exists and the service call fails.
     */
    suspend fun getMedicinesWithNetworkStatus(): Flow<Result<List<Medicine>>>
}
