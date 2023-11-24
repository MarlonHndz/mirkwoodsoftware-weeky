package com.mirkwoodsoftware.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mirkwoodsoftware.domain.models.Medicine
import com.mirkwoodsoftware.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun MedicineListContent(
    medicineList: List<Medicine>,
    viewModel: MedicineViewModel = koinViewModel()
) {
    val selectedCategory by viewModel.selectedCategory.observeAsState()

    val filteredMedicines =
        medicineList.takeIf { selectedCategory != null }?.filter { it.category!!.type == selectedCategory }
            ?: medicineList

    LazyColumn {
        items(filteredMedicines.size) { index ->
            val medicine = filteredMedicines[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medication_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(text = medicine.name, style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = medicine.category!!.name,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
