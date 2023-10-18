package com.mirkwoodsoftware.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mirkwoodsoftware.presentation.R

sealed class Screen(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    object Home : Screen(
        route = "Home",
        title = R.string.screen_home,
        icon = R.drawable.home_icon
    )

    object MedDetail : Screen(
        route = "MedicineDetail",
        title = R.string.screen_Medicine_Detail,
        icon = R.drawable.home_icon
    )

    object FormAddMedicine : Screen(
        route = "FormAddMedicine",
        title = R.string.screen_form_add_medicine,
        icon = R.drawable.form_add_icon
    )

    object History : Screen(
        route = "History",
        title = R.string.screen_history,
        icon = R.drawable.history_icon
    )
}
