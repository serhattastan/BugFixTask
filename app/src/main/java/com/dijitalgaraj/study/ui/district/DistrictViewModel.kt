package com.dijitalgaraj.study.ui.district

import com.dijitalgaraj.study.base.BaseActionState
import com.dijitalgaraj.study.base.BaseViewModel
import com.dijitalgaraj.study.models.Place

class DistrictViewModel : BaseViewModel<BaseActionState>(
    DistrictActionState.Init
) {
 var place : Place? = null

}