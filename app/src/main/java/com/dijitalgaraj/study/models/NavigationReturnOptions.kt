package com.dijitalgaraj.study.models

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class NavigationReturnOptions(
    @NavigationRes val navigation: Int,
    @IdRes val startFragment: Int?,
    var args: Bundle? = null,
    var returnable: Boolean = true,
) : Parcelable