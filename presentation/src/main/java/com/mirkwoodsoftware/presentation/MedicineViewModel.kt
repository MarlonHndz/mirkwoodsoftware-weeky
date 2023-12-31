package com.mirkwoodsoftware.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkwoodsoftware.domain.models.Medicine
import com.mirkwoodsoftware.domain.useCases.MedicineUseCase
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val medicineUseCase: MedicineUseCase
) : ViewModel() {

    private val _medicines = MutableLiveData<List<Medicine>>()
    val medicines: LiveData<List<Medicine>>
        get() = _medicines

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            medicineUseCase.getMedicines().collect() {
                _medicines.postValue(it)
            }
        }
    }
}
