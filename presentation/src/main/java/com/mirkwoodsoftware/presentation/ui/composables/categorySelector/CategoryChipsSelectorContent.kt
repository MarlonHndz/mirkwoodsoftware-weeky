package com.mirkwoodsoftware.presentation.ui.composables.categorySelector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirkwoodsoftware.domain.models.MedCategory
import com.mirkwoodsoftware.presentation.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChipsSelectorContent(
    showTips: Boolean = true,
    categories: List<MedCategory>,
    onCategorySelected: (MedCategory?) -> Unit
) {
    var selectedChipIndex by remember { mutableIntStateOf(-1) }
    var chipsEnabled by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    fun handleChipSelection(index: Int) {
        if (chipsEnabled && index != selectedChipIndex) {
            selectedChipIndex = index
            chipsEnabled = false // Deshabilita los otros chips al seleccionar uno
            onCategorySelected(categories[index])
        } else if (index == selectedChipIndex) {
            chipsEnabled = true
            selectedChipIndex = -1 // Habilita los otros chips
            onCategorySelected(null)
        }
    }

    LazyHorizontalStaggeredGrid(
        modifier = Modifier
            .height(180.dp)
            .padding(top = 5.dp),
        rows = StaggeredGridCells.Fixed(3),
        horizontalItemSpacing = 10.dp,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            val isSelected = index == selectedChipIndex

            val tooltipState = rememberTooltipState(isPersistent = true)

            TooltipBox(
                positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
                state = tooltipState,
                tooltip = {
                    RichTooltip(
                        title = {
                            Text(
                                text = category.name,
                                fontSize = 22.sp
                            )
                        },
                        text = {
                            Text(
                                text = category.description + "\n\nEjemplo: " + category.examples,
                                fontSize = 17.sp
                            )
                        },
                        action = {
                            TextButton(
                                onClick = {
                                    handleChipSelection(index)
                                    scope.launch { tooltipState.dismiss() }
                                }
                            ) {
                                Text(text = "Seleccionar", fontSize = 18.sp)
                            }
                        },
                        colors = TooltipDefaults.richTooltipColors(
                            containerColor = colorResource(id = R.color.category_selector_tooltip_background)
                        )
                    )
                }
            ) {

                ElevatedFilterChip(
                    modifier = Modifier.fillMaxSize(),
                    enabled = isSelected || chipsEnabled,
                    selected = isSelected,
                    onClick = {
                        if (showTips && !isSelected) {
                            scope.launch { tooltipState.show() }
                        } else {
                            handleChipSelection(index)
                        }
                    },
                    label = {
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = category.name,
                            fontSize = 18.sp
                        )
                    },
                    leadingIcon = if (isSelected) {
                        {
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else null
                )
            }
        }
    }
}
