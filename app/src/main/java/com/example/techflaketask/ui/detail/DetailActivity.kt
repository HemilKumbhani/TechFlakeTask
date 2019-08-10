package com.example.techflaketask.ui.detail

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import com.example.techflaketask.R
import com.example.techflaketask.di.component.ActivityComponent
import com.example.techflaketask.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    private var mIsShowingSystemUi = false
    private var mIsFullScreen = false

    override fun getLayout(): Int {
        return R.layout.activity_detail

    }

    override fun setUp(savedInstanceState: Bundle?) {

        val videoUrl=intent.getStringExtra("video_url")
        val videoPlayerFragment = VideoPlayerFragment.newInstance(
           videoUrl)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frmVideoContainer, videoPlayerFragment, VideoPlayerFragment.TAG)
            .commit()
        ivFullScreen.setOnClickListener {
            if (!mIsFullScreen) {
                mIsFullScreen = true
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                mIsFullScreen = false
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

    }

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleSystemUIVisiblity()
        /*when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {

                Handler().postDelayed({
                    if (requestedOrientation === ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }, 5000)
            }
            Configuration.ORIENTATION_PORTRAIT -> {

                Handler().postDelayed({
                    if (requestedOrientation === ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }, 5000)
            }
        }*/
    }
    private fun handleSystemUIVisiblity() {
        if (mIsShowingSystemUi) {
            mIsShowingSystemUi = false
            mIsFullScreen = true
            hideSystemUI()
            ivFullScreen.setImageResource(R.drawable.ic_video_small_screen)
        } else {
            mIsShowingSystemUi = true
            mIsFullScreen = false
            showSystemUI()
            ivFullScreen.setImageResource(R.drawable.ic_video_full_screen)
        }
    }
    private fun hideSystemUI() {
        // Set the IMMERSIVE report_flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or // hide nav bar

                View.SYSTEM_UI_FLAG_FULLSCREEN or // hide status bar

                View.SYSTEM_UI_FLAG_IMMERSIVE
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

}
