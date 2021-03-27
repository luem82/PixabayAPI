package com.example.pixabayapi.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pixabayapi.R
import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.datas.model.VideoDetail
import com.example.pixabayapi.fragments.ShowPhotoFragment
import com.example.pixabayapi.fragments.ShowVideoFragment
import kotlinx.android.synthetic.main.item_photo.view.*
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter(
    private val datas: MutableList<VideoDetail>
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_video, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    inner class VideoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun onBind(videoDetail: VideoDetail) {
            itemView.apply {
                var path = "https://i.vimeocdn.com/video/${videoDetail.pictureId}_295x166.jpg"
                Glide.with(iv_video_thumb).load(path)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            avi_video_load.hide()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            avi_video_load.hide()
                            iv_play_video.visibility = View.VISIBLE
                            return false
                        }

                    })
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(iv_video_thumb)


                setOnClickListener {

                    var dialog = ShowVideoFragment.newInstance(datas[adapterPosition])
                    var fm = (it.context as AppCompatActivity).supportFragmentManager
                    dialog.show(fm, "dialog")
                }
            }
        }
    }
}