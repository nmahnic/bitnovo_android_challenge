package com.nicomahnic.bitnovochallenge.models

class Castle(
//    var windows: MutableList<OpenWindow> = mutableListOf(),
    var windows: MutableList<ClosedWindow> = mutableListOf(),
) {

    fun createWindows(quantity: Int){
        for( windowId in  (quantity downTo 1) ){
//            this.windows.add(0, OpenWindow( windowId ) )
            this.windows.add(0, ClosedWindow( windowId ) )
        }
        reset()
    }

    fun reset(){
        windows.forEach {
            it.visitor = null
            it.reset()
        }
    }

    fun getWindowStatus() = windows.map { if (it.status) "ABIERTO" else "CERRADO" }

    fun getVisitorWhoWon() = windows.filter {
        it.status &&
        windows.indexOf(it)-1 >= 0 &&
        windows.indexOf(it)+1 < windows.size &&
        !windows[windows.indexOf(it)-1].status &&
        !windows[windows.indexOf(it)+1].status
    }.map { it.id }

    fun getVisitorWhoWon2() = windows.filter { it.status }.map { it.id }

    fun flushVisitors(){
        repeat(windows.size) {
            for (window in windows.reversed()) {
                if (windows.indexOf(window) + 1 == 64) continue
                windows[windows.indexOf(window) + 1].visitor = window.visitor
                doRules()
            }
            windows[0].visitor = null
        }
    }

    fun flushVisitor(){
        for (window in windows.reversed()) {
            if (windows.indexOf(window) + 1 >= 64) {
                windows[windows.indexOf(window)].visitor = null
            }else {
                windows[windows.indexOf(window) + 1].visitor = window.visitor
            }
        }
        doRules()
        windows[0].visitor = null
    }

    fun shiftVisitor(visitor: Visitor){
        if(visitor.id <= 64){
            val firstWindowEmpty = windows.find { it.visitor == null }
            val firstWindowEmptyIndex = windows.indexOf(firstWindowEmpty)

            for (shiftVisitor in (firstWindowEmptyIndex downTo 0)) {
                if(shiftVisitor+1 == 64) continue
                windows[shiftVisitor + 1].visitor = windows[shiftVisitor].visitor
            }
            windows[0].visitor = visitor

            doRules()
        }
    }

    private fun doRules(){
        visitorNumber1Rule()
        visitorNumber2Rule()
        visitorNumber3Rule()
        visitorNumber4Rule()
        visitorNumber64Rule()
    }

    private fun visitorNumber1Rule(){
        windows.find { it.visitor?.braceletNumber == 1 }?.let{ windowVisitorNumber1 ->
            windowVisitorNumber1.openLeftPanel()
        }
    }

    private fun visitorNumber2Rule(){
        windows.find { it.visitor?.braceletNumber == 2 }?.let { windowVisitorNumber2 ->
            if(windowVisitorNumber2.id % 2 == 0){
                windowVisitorNumber2.openRightPanel()
            }
        }
    }

    private fun visitorNumber3Rule(){
        windows.find { it.visitor?.braceletNumber == 3 }?.let { windowVisitorNumber3 ->
            if(windowVisitorNumber3.id % 3 == 0){
                windowVisitorNumber3.closeLeftPanel()
                windowVisitorNumber3.openLeftPanel()
            }
        }
    }

    private fun visitorNumber4Rule(){
        windows.find { it.visitor?.braceletNumber == 4 }?.let { windowVisitorNumber4 ->
            if(windowVisitorNumber4.id % 4 == 0){
                windowVisitorNumber4.openRightPanel()
                windowVisitorNumber4.closeLeftPanel()
            }
        }
    }

    private fun visitorNumber64Rule(){
        windows.find { it.visitor?.braceletNumber == 64 }?.let { windowVisitorNumber4 ->
            if(windowVisitorNumber4.id == 64){
                windowVisitorNumber4.toogleRightPanel()
            }
        }
    }

}
