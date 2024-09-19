package com.dijitalgaraj.study.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dijitalgaraj.study.base.BaseActionState
import com.dijitalgaraj.study.base.BaseViewModel
import com.dijitalgaraj.study.models.Place
import java.util.Locale

class CityViewModel : BaseViewModel<BaseActionState>(
    CityActionState.Init
) {
    lateinit var baseCityList : List<Place>
    var selectedItem: Place?=null
    private val _filteredCityList = MutableLiveData<List<Place>>()
    val filteredCityList: LiveData<List<Place>> get() = _filteredCityList

    fun search(text:String) {
        val trLocale = Locale("tr", "TR")
        val filterData = baseCityList.filter {
            it.title.lowercase(trLocale).contains(text.lowercase(trLocale))
        }
        _filteredCityList.value = filterData
    }
}
