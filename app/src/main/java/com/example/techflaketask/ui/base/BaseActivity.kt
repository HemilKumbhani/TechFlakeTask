package com.example.techflaketask.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techflaketask.AppController
import com.example.techflaketask.di.component.ActivityComponent
import com.example.techflaketask.di.component.DaggerActivityComponent
import com.example.techflaketask.di.module.ActivityModule

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BaseView {


    abstract fun getLayout(): Int


    override fun hideLoading() {

    }

    override fun showLoading() {

    }

    @Inject
    lateinit var mComponentDisposable: CompositeDisposable

    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivityComponent = DaggerActivityComponent.builder()
            .appComponent((application as AppController).mAppComponent)
            .activityModule(ActivityModule(this))
            .build()
        injectComponents(mActivityComponent)

        super.onCreate(savedInstanceState)
        if (getLayout() != 0) {
            setContentView(getLayout())
            setUp(savedInstanceState)

            mActivityComponent.inject(this)

        } else {
            throw Exception("Layout must not be empty")
        }
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    abstract fun setUp(savedInstanceState: Bundle?)


    abstract fun injectComponents(mActivityComponent: ActivityComponent)
}