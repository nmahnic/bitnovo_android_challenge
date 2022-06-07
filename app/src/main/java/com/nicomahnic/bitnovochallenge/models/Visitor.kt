package com.nicomahnic.bitnovochallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Visitor(
    val id: Int,
    val braceletNumber: Int = id,
) : Parcelable
