package com.example.techflaketask.ui.Home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techflaketask.R
import com.example.techflaketask.data.api.response.Data
import com.example.techflaketask.data.api.response.GifsResponse
import com.example.techflaketask.data.repositary.Rating
import com.example.techflaketask.ui.base.BaseFragment
import kotlinx.android.synthetic.main.controler_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment(), HomeView, TrendingAdapter.onUpVote, TrendingAdapter.onDownVote {

    private lateinit var mActivity: HomeActivity
    private var pageNo: Int = 0
    private var list: ArrayList<Data> = arrayListOf()

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun getLayoutId(): Int {
        return R.layout.controler_home
    }

    private lateinit var mAdapter: TrendingAdapter


    override fun setupView(view: View, savedInstanceState: Bundle?) {
        mActivity = activity as HomeActivity
        getActivityComponent()?.inject(this)
        viewModel.onAttachView(this)

        val manager = GridLayoutManager(mActivity, 2)
        recyclerGiphy.layoutManager = manager

        mAdapter = TrendingAdapter(mActivity, this, this)
        recyclerGiphy.adapter = mAdapter
        viewModel.getUpDownTotalCount()

        recyclerGiphy.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.getChildCount()
                val totalItemCount = manager.getItemCount()
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                // Load more if we have reach the end to the recyclerView
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    pageNo++
                    viewModel.getGif(pageNo)
                }
            }
        })


    }

    override fun onUpVoteClick(rating: Rating, position: Int) {
        viewModel.onUpVoteClick(rating, position)


    }

    override fun onDownVoteClick(rating: Rating, position: Int) {
        viewModel.onDownVoteClick(rating, position)
    }

    override fun onGifsAvailabale(it: GifsResponse) {
        list.addAll(it.data)
        for (i in upvoteList) {
            for(j in it.data)
            if (j.id== i.Id) {
                j.isUpvote = 1
            }
        }
        for (i in downvoteList) {
            for (j in it.data)
                if (j.id == i.Id) {
                    j.isDownVote = 1

                }
        }
        mAdapter.onDataAvailable(list)

    }

    override fun onUpVotePerformed(position: Int) {
        list[position].isUpvote = 1
        list[position].isDownVote = 0
        mAdapter.notifyItemChanged(position)
        viewModel.getUpDownTotalCount()

    }

    override fun onDownVotePerformed(position: Int) {
        list[position].isDownVote = 1
        list[position].isUpvote = 0
        mAdapter.notifyItemChanged(position)
        viewModel.getUpDownTotalCount()


    }

    private lateinit var downvoteList: List<Rating>

    override fun onDownRatingAvailable(ratingList: List<Rating>) {
        downvoteList=ratingList
        downvoteCount.setText("DownVote " + ratingList.size)


    }

    private lateinit var upvoteList: List<Rating>

    override fun onUpVoteRatingAvailable(ratingList: List<Rating>) {
        upvoteList=ratingList
        upvoteCount.setText("UpVote " + ratingList.size)
    }


}