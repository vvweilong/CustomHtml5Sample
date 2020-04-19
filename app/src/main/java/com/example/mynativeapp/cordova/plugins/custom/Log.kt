package com.example.mynativeapp.cordova.plugins.custom

import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaArgs
import org.apache.cordova.CordovaPlugin
import org.json.JSONArray

class Log: CordovaPlugin() {


    override fun execute(
        action: String?,
        args: JSONArray?,
        callbackContext: CallbackContext?
    ): Boolean {
        android.util.Log.i("Log","execute2")
        return true
    }

}