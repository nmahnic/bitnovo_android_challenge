package com.nicomahnic.bitnovochallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


interface Window {

    val id: Int
    var leftPanel: Boolean
    var rightPanel: Boolean
    var status: Boolean
    var visitor: Visitor?

    fun openRightPanel(){
        rightPanel = true
        setWindowStatus()
    }

    fun openLeftPanel(){
        leftPanel = true
        setWindowStatus()
    }

    fun closeRightPanel(){
        rightPanel = false
        setWindowStatus()
    }

    fun closeLeftPanel(){
        leftPanel = false
        setWindowStatus()
    }

    fun toogleRightPanel(){
        rightPanel = !rightPanel
        setWindowStatus()
    }

    fun toogleLeftPanel(){
        leftPanel = !leftPanel
        setWindowStatus()
    }

    private fun setWindowStatus(){
        status = leftPanel && rightPanel
    }

    fun closeWindow(){
        closeLeftPanel()
        closeRightPanel()
    }

    fun openWindow(){
        openLeftPanel()
        openRightPanel()
    }

    fun reset()

}
