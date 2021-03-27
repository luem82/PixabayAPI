package com.example.pixabayapi.fragments


import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.example.pixabayapi.R
import com.example.pixabayapi.activities.SearchActivity
import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.utils.Helpers
import kotlinx.android.synthetic.main.fragment_show_photo.*
import kotlinx.android.synthetic.main.item_photo.*

private const val ARG_PARAM1 = "param1"

class ShowPhotoFragment : DialogFragment() {

    private var photoDetail: PhotoDetail? = null

    companion object {
        @JvmStatic
        fun newInstance(photoDetail: PhotoDetail) =
            ShowPhotoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, photoDetail)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoDetail = it.getSerializable(ARG_PARAM1) as PhotoDetail
        }
        setStyle(STYLE_NORMAL, R.style.AppTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(iv_preview).load(photoDetail!!.webformatURL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    avi_photo_review.hide()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    avi_photo_review.hide()
                    return false
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(iv_preview)

        loadPhotoInfo()

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
            .setTitle("Tải xuống hình ảnh")
            .setMessage("Bạn có muốn tải về ảnh này hay không ?")
            .setIcon(R.drawable.ic_file_download_black_24dp)
            .setNegativeButton("Không", null)
            .setPositiveButton("Có", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                    requestStoragePermissionWithAction {
                        Helpers.downloadPhoto(
                            photoDetail!!.largeImageURL, photoDetail!!.user,
                            photoDetail!!.id, context!!
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
        bundle.putString("type", "photo")
        bundle.putString("key", key.replace(" ", "+"))
        bundle.putString("title", key)
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    private fun loadPhotoInfo() {

        tv_tag_1.text = Helpers.cutTags(photoDetail!!.tags)[0]
        tv_tag_2.text = Helpers.cutTags(photoDetail!!.tags)[1]
        tv_tag_3.text = Helpers.cutTags(photoDetail!!.tags)[2]

        tv_views.text = Helpers.parseIntToString(photoDetail!!.views)
        tv_likes.text = Helpers.parseIntToString(photoDetail!!.likes)
        tv_favorites.text = Helpers.parseIntToString(photoDetail!!.favorites)
        tv_downs.text = Helpers.parseIntToString(photoDetail!!.downloads)

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
