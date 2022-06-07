package com.nicomahnic.bitnovochallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClosedWindow(
    override val id: Int,
    override var leftPanel: Boolean = false,
    override var rightPanel: Boolean = false,
    override var status: Boolean = false,
    override var visitor: Visitor? = null
) : Parcelable , Window {

    override fun reset() {
        super.closeWindow()
    }
}

