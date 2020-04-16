package com.example.mynativeapp.jsbridge

import android.util.Log

class ToastBridge:BaseBridge() {
    override fun execute(data: BaseData) {
        Log.i(bridgeName,"execute :")
    }
}