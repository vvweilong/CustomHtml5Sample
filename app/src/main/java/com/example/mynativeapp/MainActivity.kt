package com.example.mynativeapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import org.apache.cordova.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), CordovaInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val container = findViewById<ViewGroup>(R.id.container)
        val createEngine = CordovaWebViewImpl.createEngine(this, CordovaPreferences())
        val cordovaWebViewImpl = CordovaWebViewImpl(createEngine)
        container.addView(cordovaWebViewImpl.view)
        cordovaWebViewImpl.init(this)
    }

    override fun setActivityResultCallback(plugin: CordovaPlugin?) {
    }

    override fun requestPermissions(
        plugin: CordovaPlugin?,
        requestCode: Int,
        permissions: Array<out String>?
    ) {
    }

    override fun startActivityForResult(
        command: CordovaPlugin?,
        intent: Intent?,
        requestCode: Int
    ) {
    }

    override fun getActivity(): Activity {
        return this
    }

    override fun onMessage(id: String?, data: Any?): Any {
        return ""
    }

    override fun getThreadPool(): ExecutorService {
        return Executors.newFixedThreadPool(2)
    }

    override fun getContext(): Context {
        return this
    }

    override fun hasPermission(permission: String?): Boolean {
        return false
    }

    override fun requestPermission(plugin: CordovaPlugin?, requestCode: Int, permission: String?) {
    }
}
