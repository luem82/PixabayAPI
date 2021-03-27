package com.example.pixabayapi.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pixabayapi.R
import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.fragments.ShowPhotoFragment
import com.kodmap.app.library.PopopDialogBuilder
import com.kodmap.app.library.constant.ScaleType
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter(
    private val datas: MutableList<PhotoDetail>
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    inner class PhotoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun onBind(photoDetail: PhotoDetail) {
            itemView.apply {
                Glide.with(iv_photo_thumb).load(photoDetail.previewURL)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            avi_photo_load.hide()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            avi_photo_load.hide()
                            return false
                        }

                    })
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(iv_photo_thumb)


                setOnClickListener {
                    //                    Log.e("previewURL", photoDetail.previewURL)
//                    Log.e("webformatURL", photoDetail.webformatURL)
//                    Log.e("largeImageURL", photoDetail.largeImageURL)
                    showPopupMenu(it, adapterPosition)
                }
            }
        }
    }

    private fun showPopupMenu(it: View?, adapterPosition: Int) {
        var popup = PopupMenu(it!!.context, it)
        popup.inflate(R.menu.menu_photo_item)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.photo_info_detail -> {
                    openInfoDetail(adapterPosition, it)
                    true
                }
                R.id.photo_slide_show -> {
                    onpenSlideShow(it, adapterPosition)
                    true
                }
                else -> true
            }
        }

        popup.show()
    }

    private fun openInfoDetail(adapterPosition: Int, it: View) {
        var dialog = ShowPhotoFragment.newInstance(datas[adapterPosition])
        var fm = (it.context as AppCompatActivity).supportFragmentManager
        dialog.show(fm, "dialog")
    }

    private fun onpenSlideShow(it: View?, adapterPosition: Int) {

        var list = arrayListOf<String>()
        for (photo: PhotoDetail in datas) {
            list.add(photo.webformatURL)
        }

        val dialog = PopopDialogBuilder(it!!.context)
            .setList(list, adapterPosition)
            .setHeaderBackgroundColor(R.color.colorAccent)
            .setDialogBackgroundColor(R.color.colorAccent)
            .setCloseDrawable(R.drawable.ic_close_white_24dp)
            .setLoadingView(R.layout.layout_slide_show)
            .setDialogStyle(R.style.DialogStyle)
            .showThumbSlider(true)
            .setSliderImageScaleType(ScaleType.FIT_CENTER)
            .build()

        dialog.show()
    }
}