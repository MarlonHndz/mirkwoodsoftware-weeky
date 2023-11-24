package com.mirkwoodsoftware.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirkwoodsoftware.domain.models.MedCategory
import org.koin.androidx.compose.koinViewModel

@Composable
fun MedCategoriesRowContent(
    categories: List<MedCategory>,
    viewModel: MedicineViewModel = koinViewModel()
) {
    val selectedCategory by viewModel.selectedCategory.observeAsState()
    LazyRow {
        items(categories.size) { index ->
            val category = categories[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {  },
                elevation = CardDefaults.cardElevation(6.dp),
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontSize = 15.sp,
                    text = category.name
                )
            }
        }
    }
}

@Preview
@Composable
fun MedCategoriesRowContentPreview() {
    // MedCategoriesRowContent(Medicine.MedCategory.values().toList())
}
