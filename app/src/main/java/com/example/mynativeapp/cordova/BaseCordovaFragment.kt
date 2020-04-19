package com.example.mynativeapp.cordova

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.mynativeapp.R
import org.apache.cordova.*
import java.util.*
import java.util.concurrent.ExecutorService

/**
 * 使用cordova 进行js 通信的 基类
 * */
class BaseCordovaFragment : Fragment() {

    val TAG = "BaseCordovaFragment"

    var cordovaWebViewImpl:CordovaWebViewImpl?=null
    var nativeWebView:WebView?=null
    var cordovaWebViewEngine:CordovaWebViewEngine?=null
    var cordovaPreferences :CordovaPreferences?=null
    val configXmlParser = ConfigXmlParser()
    var pluginEntries = ArrayList<PluginEntry>()
    var cordovaInterfaceImpl:CordovaInterfaceImpl?=null


    private fun loadConfig(){
        configXmlParser.parse(activity)//读 xml 文件

        cordovaPreferences = configXmlParser.preferences

        pluginEntries=configXmlParser.pluginEntries
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //加载 config
        loadConfig()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            cordovaInit()
            val root = inflater.inflate(R.layout.fragment_base_cordova, container, false)
            return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webContainer = view.findViewById<FrameLayout>(R.id.root_container)
        webContainer.addView(cordovaWebViewImpl?.view,FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT))
        cordovaWebViewImpl?.loadUrl("file:///android_asset/cordova_index.html")
    }

    private fun cordovaInit() {
        if (cordovaWebViewImpl == null) {//如果没有创建
            cordovaWebViewEngine = CordovaWebViewImpl.createEngine(context, cordovaPreferences)
            cordovaWebViewImpl = CordovaWebViewImpl(cordovaWebViewEngine)

        }
        if (cordovaWebViewImpl?.isInitialized == false) {//如果没进行初始化
            cordovaInterfaceImpl = CordovaInterfaceImpl(activity)
            cordovaWebViewImpl?.init(cordovaInterfaceImpl,pluginEntries,cordovaPreferences)
            cordovaInterfaceImpl?.onCordovaInit(cordovaWebViewImpl?.pluginManager)
        }
    }
}
