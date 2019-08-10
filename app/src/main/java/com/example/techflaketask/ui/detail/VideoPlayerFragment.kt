package com.example.techflaketask.ui.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.techflaketask.BuildConfig
import com.example.techflaketask.R
import com.example.techflaketask.ui.base.BaseFragment
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_video_player.*


class VideoPlayerFragment @SuppressLint("ValidFragment")
private constructor() : Fragment(), Player.EventListener {



    companion object {

        private const val EXTRA_VIDEO_URL = "extra_video_url"
        private const val EXTRA_IS_FULLSCREEN = "extra_is_fullscreen"

        @JvmField
        val TAG = VideoPlayerFragment::class.java.simpleName

        /**
         * Create a instance of video player with the help of video url and its duration
         */
        @JvmStatic
        @JvmOverloads
        fun newInstance(videoUrl: String, isFullScreen: Boolean = false): VideoPlayerFragment {
            val videoPlayerFragment = VideoPlayerFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_VIDEO_URL, videoUrl)
            bundle.putBoolean(EXTRA_IS_FULLSCREEN, isFullScreen)
            videoPlayerFragment.arguments = bundle
            return videoPlayerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private lateinit var videoUrl: String
    private var videoDuration = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

    private lateinit var player: SimpleExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoUrl = arguments?.getString(EXTRA_VIDEO_URL)!!
        val isFullScreen = arguments?.getBoolean(EXTRA_IS_FULLSCREEN)

        ivFullScreen.setImageResource(
                if (isFullScreen!!)
                    R.drawable.ic_video_small_screen
                else
                    R.drawable.ic_video_full_screen
        )

        // 1. Create a default TrackSelector
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        // 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)

        // 3.Bind the player to the view.
        exoPlayer.player = player

        // 4.Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, BuildConfig.APPLICATION_ID),
                bandwidthMeter)

        // 5.This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl))


        // 6.Prepare the player with the source.
        player.playWhenReady = false
        player.prepare(videoSource)
        //TODo remove changes
        val playbackParameters: PlaybackParameters = PlaybackParameters(0.5f, 1f)
        player.playbackParameters = playbackParameters

        player.addListener(this)

        exoPlayer.setControllerVisibilityListener {
            if (imgPlay.visibility==View.VISIBLE) {
                exoPlayer.hideController()
            }
        }
        exoPlayer.hideController()

        imgPlay.setOnClickListener {
            imgPlay.visibility=View.GONE
            exoPlayer.showController()
            //restart the video when video is ended by seeking video to start position
            if (player.playbackState == Player.STATE_ENDED)
                player.seekTo(0)
            player.playWhenReady = true
        }

        ivFullScreen.setOnClickListener {
            if (isFullScreen)
                activity?.finish()
//            else
////                FullScreenVideoActivity.launchActivity(activity!!, videoUrl)
        }

    }

    override fun onPause() {
        super.onPause()
        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
    }

    override fun onSeekProcessed() {
    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
//        activity?.toast("Unable to Play the video")
    }

    override fun onLoadingChanged(isLoading: Boolean) {
    }

    override fun onPositionDiscontinuity(reason: Int) {
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        //Show loader is player is buffering
        if (Player.STATE_BUFFERING == playbackState && imgPlay.visibility!=View.VISIBLE) pbLoader.visibility=View.VISIBLE else pbLoader.visibility=View.GONE

        //Show play button and hide controller when media is ended
        if (Player.STATE_ENDED == playbackState) {
            imgPlay.visibility=View.VISIBLE
            exoPlayer.hideController()
        }
    }

}