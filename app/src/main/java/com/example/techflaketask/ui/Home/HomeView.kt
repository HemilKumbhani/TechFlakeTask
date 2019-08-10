package com.example.techflaketask.ui.Home

import com.example.techflaketask.data.api.response.GifsResponse
import com.example.techflaketask.ui.base.BaseView


interface HomeView : BaseView {
    fun onGifsAvailabale(it: GifsResponse)

    fun onUpVotePerformed(position: Int)

    fun onDownVotePerformed(position: Int)

    fun onDownRatingAvailable(size: Int)

    fun onUpVoteRatingAvailable(size: Int)



}