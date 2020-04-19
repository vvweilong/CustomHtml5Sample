package com.example.mynativeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.mynativeapp.cordova.BaseCordovaFragment
import com.example.mynativeapp.jsbridge.BaseJsBridgeFragment
import org.apache.cordova.CordovaActivity

class MainActivity : AppCompatActivity() {

    val pages = ArrayList<Fragment>()

    val viewPager :ViewPager by lazy {
        findViewById<ViewPager>(R.id.viewpager)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pages.add(BaseCordovaFragment())
        pages.add(BaseJsBridgeFragment())
        viewPager.adapter = PageAdapter(supportFragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

    }
    fun jump(v:View?){
        startActivity(
            Intent(this,CordovaActivity::class.java)
        )
    }

    inner class PageAdapter(fm: FragmentManager,behavor:Int) : FragmentStatePagerAdapter(fm,behavor) {
        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0->{"cordova"}
                else->{"jsBridge"}
            }
        }
    }


}
