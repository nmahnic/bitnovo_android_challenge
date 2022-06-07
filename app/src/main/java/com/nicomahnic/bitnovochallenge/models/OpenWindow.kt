package com.nicomahnic.bitnovochallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OpenWindow(
    override val id: Int,
    override var leftPanel: Boolean = true,
    override var rightPanel: Boolean = true,
    override var status: Boolean = true,
    override var visitor: Visitor? = null
) : Parcelable , Window {

    override fun reset() {
        super.openWindow()
    }
}

