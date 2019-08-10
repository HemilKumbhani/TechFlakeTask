package com.example.techflaketask.ui.Home


import com.example.techflaketask.data.AppDataManger
import com.example.techflaketask.data.repositary.Rating
import com.example.techflaketask.di.PerActivity
import com.example.techflaketask.ui.base.BaseView
import com.example.techflaketask.ui.base.BaseViewModel
import com.example.techflaketask.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerActivity
class HomeViewModel
@Inject constructor(
    appDataManger: AppDataManger,
    mSchedulerProvider: SchedulerProvider,
    mCompositeDisposable: CompositeDisposable
) : BaseViewModel(appDataManger, mSchedulerProvider, mCompositeDisposable) {

    var mView: HomeView? = null

    override fun onAttachView(view: BaseView) {
        super.onAttachView(view)
        mView = view as HomeView
        getGif(0)


    }

    fun getGif(page: Int) {
        mCompositeDisposable.add(
            appDataManger.getGifs("PHv8x3lJadlUp2UzTklT9oEJnUSr9PQA", "20", (page * 20).toString())
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({
                    mView?.onGifsAvailabale(it)

                }, {

                })
        )

    }

    fun onUpVoteClick(rating: Rating, position: Int) {
        mCompositeDisposable.add(appDataManger.insertRating(rating)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe({
                mView?.onUpVotePerformed(position)


            }, {

            })
        )

    }

    fun getUpDownTotalCount() {
        mCompositeDisposable.add(appDataManger.getUpvoteRating()
            .observeOn(mSchedulerProvider.io())
            .concatMap {

                mView?.onUpVoteRatingAvailable(it)
                return@concatMap appDataManger.getDownvoteRating()

            }
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe({
                mView?.onDownRatingAvailable(it)

            }, {

            })
        )
    }


    fun onDownVoteClick(rating: Rating, position: Int) {
        mCompositeDisposable.add(appDataManger.insertRating(rating)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe({
                mView?.onDownVotePerformed(position)


            }, {

            })
        )
    }

}

