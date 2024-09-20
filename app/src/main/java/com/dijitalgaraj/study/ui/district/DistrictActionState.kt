package com.dijitalgaraj.study.ui.district

import com.dijitalgaraj.study.base.BaseActionState

sealed class DistrictActionState : BaseActionState() {

    data object Init : DistrictActionState()
}