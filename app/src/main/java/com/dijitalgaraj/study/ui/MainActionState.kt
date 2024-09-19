package com.dijitalgaraj.study.ui

import com.dijitalgaraj.study.base.BaseActionState

sealed class MainActionState : BaseActionState() {
    object Init : MainActionState()
}