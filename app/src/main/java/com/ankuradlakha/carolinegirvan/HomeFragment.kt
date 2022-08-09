package com.ankuradlakha.carolinegirvan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ankuradlakha.carolinegirvan.network.ApiInterface
import com.ankuradlakha.carolinegirvan.network.CGClient
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.FileDataSource
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var exoPlayer: ExoPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        VimeoApiClient.initialize(
//            VimeoApiConfiguration.Builder("51144491039787a1e095d43fd387422f").build(),
//            Authenticator.instance()
//        )
//        val config = VimeoApiConfiguration.Builder("51144491039787a1e095d43fd387422f").build()
//        Authenticator.initialize(config)
//        val client = VimeoApiClient(
//            VimeoApiConfiguration.Builder("51144491039787a1e095d43fd387422f").build(),
//            Authenticator.instance()
//        )
//        client.fetchVideoList("/me/videos", callback = object : VimeoCallback<VideoList> {
//            override fun onError(error: VimeoResponse.Error) {
//                Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSuccess(response: VimeoResponse.Success<VideoList>) {
//                Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
//                initializePlayer()
//            }
//        }, cacheControl = null, fieldFilter = null, queryParams = null)
        initializePlayer()
        createMediaItem("https://player.vimeo.com/progressive_redirect/playback/713301534/rendition/360p/file.mp4?loc=external&oauth2_token_id=1630222730&signature=c6756f0b4e70a4252c7c6f299dfda30ad07f6c82719d70aeec82955f5d1f7de9")
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        releasePlayer()
        super.onStop()
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    private fun releasePlayer() {
        exoPlayer?.run {
            playWhenReady = false
            release()
        }
        exoPlayer = null
    }

    private fun initializePlayer() {
        context?.let { ctx ->
            if (exoPlayer == null)
                exoPlayer = ExoPlayer.Builder(ctx).build()
//            exoPlayer?.addListener(object : Player.Listener {
//                override fun onIsPlayingChanged(isPlaying: Boolean) {
//                    if (isPlaying) {
//
//                    } else {
//
//                    }
//                    super.onIsPlayingChanged(isPlaying)
//                }
//
//                override fun onPlaybackStateChanged(playbackState: Int) {
//                    if (playbackState == Player.STATE_READY) {
//                        exoPlayer?.playWhenReady = true
//                    }
//                }
//            })
            val path = "https://player.vimeo.com/progressive_redirect/playback/713301534/rendition/360p/file.mp4?loc=external&oauth2_token_id=1630222730&signature=c6756f0b4e70a4252c7c6f299dfda30ad07f6c82719d70aeec82955f5d1f7de9"
            val mediaItem =
                MediaItem.fromUri(path)
            exoPlayer?.setMediaItem(mediaItem)
            val factory = FileDataSource.Factory()
            val source = ProgressiveMediaSource.Factory(factory).createMediaSource(mediaItem)
//            val source = HlsMediaSource.Factory()
            exoPlayer?.setMediaSource(source)
            video_view.player = exoPlayer
//            CGClient.getClient().create(ApiInterface::class.java).getAllVideos()
//                .enqueue(object : Callback<JsonObject> {
//                    override fun onResponse(
//                        call: Call<JsonObject>,
//                        response: Response<JsonObject>
//                    ) {
//                        Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
////                        createMediaItem("https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
////                        createMediaItem("https://vimeo.com/706565947")
//                    }
//
//                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
//                    }
//                })
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = true
            exoPlayer?.seekTo(0, 0)
//            exoPlayer?.play()
        }
    }

    private fun createMediaItem(url: String) {
        val mediaItem: MediaItem = MediaItem.fromUri(url)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.playWhenReady = true
    }
}