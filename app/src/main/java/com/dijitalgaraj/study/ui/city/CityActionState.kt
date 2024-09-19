package com.dijitalgaraj.study.ui.city

import com.dijitalgaraj.study.base.BaseActionState

sealed class CityActionState : BaseActionState() {

    object Init : CityActionState()
}