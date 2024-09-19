package com.dijitalgaraj.study.models

sealed class BoardsClicks {
    data class ClickedDistrict(val city : String) : BoardsClicks()
}