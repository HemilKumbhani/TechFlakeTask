package com.example.techflaketask.ui.Home

import android.os.Bundle
import com.example.techflaketask.R
import com.example.techflaketask.di.component.ActivityComponent
import com.example.techflaketask.ui.base.BaseActivity
import com.bluelinelabs.conductor.Router


class HomeActivity : BaseActivity() {


    override fun getLayout(): Int {
        return R.layout.activity_home

    }

    private lateinit var router: Router

    override fun setUp(savedInstanceState: Bundle?) {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, HomeFragment()).commit()

    }

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }
}


