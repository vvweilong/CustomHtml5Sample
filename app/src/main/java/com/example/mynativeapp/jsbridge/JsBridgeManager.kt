package com.example.mynativeapp.jsbridge

import android.text.TextUtils
import android.view.TextureView
import android.widget.TextView
import com.github.lzyzsd.jsbridge.CallBackFunction

class JsBridgeManager {


    val bridgeList= HashMap<String,BaseBridge>()


    fun getDefaultBridges(){
        val toastBridge = ToastBridge()
        bridgeList.put(toastBridge.bridgeName,toastBridge)
    }

    fun dispatch(data: BaseData) {
        var targetBridge :BaseBridge?=null
        for (entry in bridgeList) {
            if (TextUtils.equals(entry.key,data.serviceName)) {
                targetBridge=entry.value
            }
        }
        targetBridge?.execute(data)
    }
}

abstract class BaseBridge(){
    val bridgeName = javaClass.simpleName
    abstract fun execute(data:BaseData)
}

data class BaseData(val serviceName:String?,val action:String?,val args:Array<String?>?,var callback:CallBackFunction?=null)