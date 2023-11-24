package com.mirkwoodsoftware.presentation.ui.composables.categorySelector

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirkwoodsoftware.domain.models.MedCategory

@Composable
fun CategorySelector(
    modifier: Modifier,
    onCategorySelected: (MedCategory) -> Unit,
    label: String,
    categories: List<MedCategory>,
) {
    var isCategoryPickerVisible by remember { mutableStateOf(false) }
    var selectedCategoryName by remember { mutableStateOf(label) }

    Column(modifier = modifier) {
        OutlinedButton(
            onClick = { isCategoryPickerVisible = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 6.dp),
                    text = selectedCategoryName,
                    fontSize = 18.sp
                )
                Icon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    imageVector = Icons.Outlined.Create,
                    contentDescription = "",
                    tint = androidx.compose.material.MaterialTheme.colors.primary
                )
            }
        }
    }
    if (isCategoryPickerVisible) {
        CategoryBottomSheetContent(
            categories = categories,
            onDismissBottomSheet = { isCategoryPickerVisible = false },
            onCategorySelected = { medCategory ->
                selectedCategoryName = medCategory?.name ?: label
                medCategory?.let { onCategorySelected(it) }
            }
        )
    }
}
