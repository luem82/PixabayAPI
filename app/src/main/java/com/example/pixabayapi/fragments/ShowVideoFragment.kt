package com.example.pixabayapi.fragments


import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.pixabayapi.R
import com.example.pixabayapi.activities.SearchActivity
import com.example.pixabayapi.datas.model.VideoDetail
import com.example.pixabayapi.utils.Helpers
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_show_video.*


private const val ARG_PARAM1 = "param1"

@Suppress("DEPRECATION")
class ShowVideoFragment : DialogFragment() {

    private var videoDetail: VideoDetail? = null
    private var simpleExoPlayer: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoDetail = it.getSerializable(ARG_PARAM1) as VideoDetail
        }
        setStyle(STYLE_NORMAL, R.style.AppTheme)
    }

    companion object {
        @JvmStatic
        fun newInstance(videoDetail: VideoDetail) =
            ShowVideoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, videoDetail)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadVideoInfo()
        initPlayerView(videoDetail!!.videos.medium.url)

        iv_back.setOnClickListener { dismiss() }

        tv_tag_1.setOnClickListener {
            openSearch(
                (it as TextView).text.toString()
            )
        }
        tv_tag_2.setOnClickListener {
            openSearch(
                (it as TextView).text.toString()
            )
        }
        tv_tag_3.setOnClickListener {
            openSearch(
                (it as TextView).text.toString()
            )
        }

        iv_download.setOnClickListener { showDialogDownload() }

    }

    private fun showDialogDownload() {

        var builder = AlertDialog.Builder(context!!)
            .setTitle("Tải xuống video")
            .setMessage("Bạn có muốn tải về video này hay không ?")
            .setIcon(R.drawable.ic_file_download_black_24dp)
            .setNegativeButton("Không", null)
            .setPositiveButton("Có", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                    requestStoragePermissionWithAction {
                        Helpers.downloadVideo(
                            videoDetail!!.videos.small.url, videoDetail!!.user,
                            videoDetail!!.id, context!!
                        )
                    }
                }

            })

        var dialog = builder.create()
        dialog.show()
    }

    private fun openSearch(key: String) {
        var intent = Intent(context, SearchActivity::class.java)
        var bundle = Bundle()
        bundle.putString("type", "video")
        bundle.putString("key", key.replace(" ", "+"))
        bundle.putString("title", key)
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    private fun loadVideoInfo() {

        tv_tag_1.text = Helpers.cutTags(videoDetail!!.tags)[0]
        tv_tag_2.text = Helpers.cutTags(videoDetail!!.tags)[1]
        tv_tag_3.text = Helpers.cutTags(videoDetail!!.tags)[2]

        tv_views.text = Helpers.parseIntToString(videoDetail!!.views)
        tv_likes.text = Helpers.parseIntToString(videoDetail!!.likes)
        tv_favorites.text = Helpers.parseIntToString(videoDetail!!.favorites)
        tv_downs.text = Helpers.parseIntToString(videoDetail!!.downloads)

    }

    private fun initPlayerView(url: String) {
        simpleExoPlayer = SimpleExoPlayer.Builder(context!!).build()
        pv_video.player = simpleExoPlayer
        val dataSourceFactory = DefaultDataSourceFactory(
            context, Util.getUserAgent(context!!, "Application Name")
        )
        val mediaSource =
            ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url))
        simpleExoPlayer!!.prepare(mediaSource)
        simpleExoPlayer!!.playWhenReady = true

        simpleExoPlayer!!.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    avi_video_review.show()
                } else if (playbackState == Player.STATE_READY) {
                    avi_video_review.hide()
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        if (simpleExoPlayer != null) {
            simpleExoPlayer!!.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (simpleExoPlayer != null) {
            simpleExoPlayer!!.release()
        }
    }

    private var onPermissionGrantedAction: (() -> Unit)? = null
    private val PERMISSION_CODE = 99
    fun requestStoragePermissionWithAction(permissionNeededAction: () -> Unit) {
        onPermissionGrantedAction = permissionNeededAction
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_CODE)
        } else {
            onPermissionGrantedAction?.invoke()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE &&
            grantResults.first() == PackageManager.PERMISSION_GRANTED
        ) {
            onPermissionGrantedAction?.invoke()
            onPermissionGrantedAction = null
        }
    }


}
