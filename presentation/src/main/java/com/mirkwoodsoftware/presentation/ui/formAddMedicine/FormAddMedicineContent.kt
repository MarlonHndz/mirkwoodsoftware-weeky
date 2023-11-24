package com.mirkwoodsoftware.presentation.ui.formAddMedicine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mirkwoodsoftware.domain.models.CategoryType
import com.mirkwoodsoftware.domain.models.MedCategory
import com.mirkwoodsoftware.presentation.R
import com.mirkwoodsoftware.presentation.ui.composables.categorySelector.CategorySelector
import com.mirkwoodsoftware.presentation.ui.composables.DateInput
import org.koin.androidx.compose.koinViewModel

@Composable
fun FormAddMedicineContent(
    viewModel: FormAddViewModel = koinViewModel()
) {
    val categories by viewModel.categories.observeAsState(emptyList())

    var medicationName by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var categoriesSelected by remember {
        mutableStateOf(
            MedCategory(
                "",
                "",
                "",
                CategoryType.MEDICATION
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = medicationName,
            onValueChange = { medicationName = it },
            label = { Text(stringResource(R.string.form_add_medicine_label_med_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        DateInput(
            modifier = Modifier.padding(bottom = 16.dp),
            onValueChange = { expirationDate = it },
            label = stringResource(R.string.form_add_medicine_label_expiration_date),
        )

        CategorySelector(
            modifier = Modifier.padding(bottom = 16.dp),
            onCategorySelected = { categoriesSelected = it },
            label = "Selecciona una categoria",
            categories = categories,
        )


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Aquí puedes manejar la lógica del botón Enviar
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(Icons.Default.Done, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.form_add_medicine_confirm_button))
        }
    }
}
