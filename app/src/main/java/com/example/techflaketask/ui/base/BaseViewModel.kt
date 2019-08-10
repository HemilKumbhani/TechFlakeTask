package com.example.techflaketask.ui.base


import androidx.lifecycle.ViewModel
import com.example.techflaketask.data.AppDataManger
import com.example.techflaketask.utils.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(val appDataManger: AppDataManger,
                         val mSchedulerProvider: SchedulerProvider,
                         val mCompositeDisposable: CompositeDisposable) : ViewModel() {

  /*  companion object {
        val TAG = BaseViewModel.javaClass.canonicalName
    }*/

    var view: BaseView? = null

    open fun isViewAttached(): Boolean {
        return view != null
    }

    open fun onAttachView(view: BaseView) {
        this.view = view
    }

    fun onDetachView() {
        mCompositeDisposable.dispose()
        view = null
    }

    open fun getBaseView(): BaseView? {
        return view
    }
}