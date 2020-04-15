package com.example.mynativeapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import org.apache.cordova.*
import java.util.concurrent.ExecutorService

/**
 * 使用cordova 进行js 通信的 基类
 * */
class BaseCordovaFragment : Fragment() {

    val TAG = "BaseCordovaFragment"

    var cordovaWebViewImpl:CordovaWebViewImpl?=null
    var nativeWebView:WebView?=null
    var cordovaWebViewEngine:CordovaWebViewEngine?=null
    val cordovaPreferences :CordovaPreferences by lazy { configXmlParser.preferences }
    val configXmlParser = ConfigXmlParser()

    var cordovaInterfaceImpl:CordovaInterfaceImpl?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //通过 config.xml 加载cordova plugins
        configXmlParser.parse(activity)
        cordovaInterfaceImpl =object :CordovaInterfaceImpl(activity){
            override fun startActivityForResult(
                command: CordovaPlugin?,
                intent: Intent?,
                requestCode: Int
            ) {
            }

            override fun setActivityResultCallback(plugin: CordovaPlugin?) {
                super.setActivityResultCallback(plugin)
            }

            override fun getActivity(): Activity {
                return super.getActivity()
            }

            override fun getContext(): Context {
                return super.getContext()
            }

            override fun onMessage(id: String?, data: Any?): Any {
                return super.onMessage(id, data)
            }

            override fun getThreadPool(): ExecutorService {
                return super.getThreadPool()
            }

            override fun onCordovaInit(pluginManager: PluginManager?) {
                super.onCordovaInit(pluginManager)
            }

            override fun onActivityResult(
                requestCode: Int,
                resultCode: Int,
                intent: Intent?
            ): Boolean {
                return super.onActivityResult(requestCode, resultCode, intent)
            }

            override fun setActivityResultRequestCode(requestCode: Int) {
                super.setActivityResultRequestCode(requestCode)
            }

            override fun onSaveInstanceState(outState: Bundle?) {
                super.onSaveInstanceState(outState)
            }

            override fun restoreInstanceState(savedInstanceState: Bundle?) {
                super.restoreInstanceState(savedInstanceState)
            }

            override fun onRequestPermissionResult(
                requestCode: Int,
                permissions: Array<out String>?,
                grantResults: IntArray?
            ) {
                super.onRequestPermissionResult(requestCode, permissions, grantResults)
            }

            override fun requestPermission(
                plugin: CordovaPlugin?,
                requestCode: Int,
                permission: String?
            ) {
                super.requestPermission(plugin, requestCode, permission)
            }

            override fun requestPermissions(
                plugin: CordovaPlugin?,
                requestCode: Int,
                permissions: Array<out String>?
            ) {
                super.requestPermissions(plugin, requestCode, permissions)
            }

            override fun hasPermission(permission: String?): Boolean {
                return super.hasPermission(permission)
            }
        }
    }

    fun loadUrl(url:String?){
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
        cordovaWebViewImpl?.loadUrl("https://www.baidu.com")
    }

    private fun cordovaInit() {
        if (cordovaWebViewImpl == null) {//如果没有创建
            cordovaWebViewEngine = CordovaWebViewImpl.createEngine(context, cordovaPreferences)
            cordovaWebViewImpl = CordovaWebViewImpl(cordovaWebViewEngine)

        }
        if (cordovaWebViewImpl?.isInitialized == false) {//如果没进行初始化
            cordovaInterfaceImpl = CordovaInterfaceImpl(activity)
            cordovaInterfaceImpl?.onCordovaInit(cordovaWebViewImpl?.pluginManager)
            cordovaWebViewImpl?.init(cordovaInterfaceImpl)
        }
    }
}
