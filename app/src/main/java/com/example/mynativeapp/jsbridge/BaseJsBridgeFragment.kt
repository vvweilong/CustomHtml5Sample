package com.example.mynativeapp.jsbridge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynativeapp.R
import com.github.lzyzsd.jsbridge.BridgeHandler
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.github.lzyzsd.jsbridge.CallBackFunction
import com.google.gson.Gson

/**
 * 使用jsbridge 进行js通信的基类 Fragment
 * */
class BaseJsBridgeFragment : Fragment(),BridgeHandler {
    val TAG = "BaseJsBridgeFragment"

    val bridgeManager = JsBridgeManager().apply { getDefaultBridges() }

    val gson = Gson()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (view == null) {
            return inflater.inflate(R.layout.fragment_base_js_bridge, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val findViewById = view.findViewById<BridgeWebView>(R.id.bridge_webview)
        findViewById.loadUrl("file:///android_asset/js_index.html")
        findViewById.setDefaultHandler(this)
    }

    override fun handler(data: String?, function: CallBackFunction?) {
        Log.i(TAG,"handler :data ${data} function ${function.toString()}")
        val dataObj = gson.fromJson<BaseData>(data, BaseData::class.java)
        dataObj.callback = function
        bridgeManager.dispatch(dataObj)
    }

}
