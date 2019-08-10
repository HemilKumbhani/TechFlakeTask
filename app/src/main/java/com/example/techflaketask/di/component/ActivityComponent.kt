package com.example.techflaketask.di.component


import com.example.techflaketask.di.PerActivity
import com.example.techflaketask.di.module.ActivityModule
import com.example.techflaketask.ui.Home.HomeActivity
import com.example.techflaketask.ui.Home.HomeFragment
import com.example.techflaketask.ui.base.BaseActivity
import com.example.techflaketask.ui.detail.DetailActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(homeActivity: HomeActivity)

    fun inject(homeFragment: HomeFragment)

    fun inject(detailActivity: DetailActivity)


}