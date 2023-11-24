package com.mirkwoodsoftware.presentation.ui.formAddMedicine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkwoodsoftware.domain.models.MedCategory
import com.mirkwoodsoftware.domain.useCases.FormAddMedicineUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormAddViewModel(
    private val formAddMedicineUseCase: FormAddMedicineUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<MedCategory>>()
    val categories: LiveData<List<MedCategory>>
        get() = _categories

    init {
        getCategoriesList()
    }
    private fun getCategoriesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                formAddMedicineUseCase.getCategories()
                    .collect {
                        _categories.postValue(it)
                    }
            }
        }
    }

}
