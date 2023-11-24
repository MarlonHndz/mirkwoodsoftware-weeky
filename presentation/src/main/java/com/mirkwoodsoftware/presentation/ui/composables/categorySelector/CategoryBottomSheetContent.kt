package com.mirkwoodsoftware.presentation.ui.composables.categorySelector

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirkwoodsoftware.domain.models.CategoryType
import com.mirkwoodsoftware.domain.models.MedCategory
import com.mirkwoodsoftware.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheetContent(
    categories: List<MedCategory>,
    onDismissBottomSheet: () -> Unit,
    onCategorySelected: (MedCategory?) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissBottomSheet() },
        containerColor = colorResource(id = R.color.category_selector_background)
    ) {
        // Sheet content
        var showTipsDescriptions by remember { mutableStateOf(false) }
        var switchCategoryType by remember { mutableStateOf(true) }
        var categoryTypeSelected by remember { mutableIntStateOf(0) }

        val categoryTypes = CategoryType.values()
        val icons = listOf(
            painterResource(id = R.drawable.medicine_icon),
            painterResource(id = R.drawable.injection_icon),
        )

        Column(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {

            MultiChoiceSegmentedButtonRow(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(50.dp),
            ) {
                categoryTypes.forEachIndexed { index, categoryType ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = categoryTypes.size
                        ),
                        icon = {
                            SegmentedButtonDefaults.Icon(active = (index == categoryTypeSelected) && !switchCategoryType) {
                                Icon(
                                    painter = icons[index],
                                    contentDescription = null,
                                    modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        checked = index == categoryTypeSelected,
                        onCheckedChange = {
                            if (switchCategoryType) {
                                categoryTypeSelected = index
                            }
                        },
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = colorResource(
                                id = R.color.category_selector_segment_btn_selected
                            )
                        )
                    ) {
                        Text(
                            text = categoryType.description,
                            fontSize = 17.sp,
                            fontWeight = FontWeight(500)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "Mostrar descripciones",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500)
                )
                Switch(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    checked = showTipsDescriptions,
                    onCheckedChange = { showTipsDescriptions = it },
                    thumbContent = if (showTipsDescriptions) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    }
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))

            CategoryChipsSelectorContent(
                showTips = showTipsDescriptions,
                categories = categories.filter {
                    when (categoryTypeSelected) {
                        0 -> it.type == CategoryType.MEDICATION
                        else -> it.type == CategoryType.UTENSIL
                    }
                },
                onCategorySelected = { medCategory ->
                    switchCategoryType = medCategory == null
                    onCategorySelected(medCategory)
                }
            )
        }
    }
}
