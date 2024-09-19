package com.dijitalgaraj.study.base

import androidx.lifecycle.ViewModel
import com.dijitalgaraj.study.utils.extensions.EventWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<ActionState : BaseActionState>(
    baseActionState: ActionState
) : ViewModel() {

    /**
     * Triggers action states in fragment
     *
     */
    private val mActionState: MutableStateFlow<EventWrapper<ActionState>> =
        MutableStateFlow(EventWrapper(baseActionState))
    val actionState: StateFlow<EventWrapper<ActionState>>
        get() = mActionState

    fun updateActionState(state: ActionState) {
        mActionState.value = EventWrapper(state)
    }

}