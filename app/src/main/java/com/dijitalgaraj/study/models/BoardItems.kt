package com.dijitalgaraj.study.models

sealed class BoardItems {
    data class DistrictItem(val items: List<String>?) : BoardItems()
    object Empty : BoardItems()
}