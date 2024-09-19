package com.dijitalgaraj.study.models

import android.content.Context
import android.os.Parcelable
import com.dijitalgaraj.study.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.InputStreamReader

@Parcelize
data class Place (
    val title:String,
    val places: List<String>,
    val colorName:String,
    val color:String
): Parcelable

fun loadPlacesFromJson(context: Context): List<Place> {
    val inputStream = context.resources.openRawResource(R.raw.places)
    val reader = InputStreamReader(inputStream)

    // JSON'u Listeye dönüştürme
    val placeListType = object : TypeToken<List<Place>>() {}.type
    val placeList: List<Place> = Gson().fromJson(reader, placeListType)

    reader.close()
    return placeList
}