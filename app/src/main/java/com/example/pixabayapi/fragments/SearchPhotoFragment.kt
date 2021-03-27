package com.example.pixabayapi.fragments


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.PhotoAdapter
import com.example.pixabayapi.adapters.ViewPagerAdapter
import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.utils.Consts
import com.example.pixabayapi.utils.FetchData
import com.example.pixabayapi.utils.Helpers
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.fragment_search_photo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchPhotoFragment : Fragment() {

    var viewPagerAdapter: ViewPagerAdapter? = null
    lateinit var keySearch: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Helpers.keyboardUtil(context!!, edtSearchPhoto, true)

        tvSearchPhoto.setOnClickListener {
            if (TextUtils.isEmpty(edtSearchPhoto.text.toString())) {
                Toast.makeText(context, "Nhập từ khóa hình ảnh cần tìm", Toast.LENGTH_SHORT).show()
            } else {
                keySearch = edtSearchPhoto.text.toString().replace(" ", "+")
                searchPhoto(keySearch)
                aviSearchPhoto.show()
                Helpers.keyboardUtil(context!!, it, false)
            }
        }

    }

    private fun searchPhoto(key: String) {

        if (viewPagerAdapter != null) {
            vpSearchPhoto.removeAllViews()
            viewPagerAdapter!!.cleanUp()
        }

        CoroutineScope(Dispatchers.Main).launch {

            var listPhoto = FetchData.searchPhotos(
                Consts.PHOTO_TYPE_PHOTO, 100, key
            )
            var listIllustration = FetchData.searchPhotos(
                Consts.PHOTO_TYPE_ILLUS, 100, key
            )
            var listVector = FetchData.searchPhotos(
                Consts.PHOTO_TYPE_VECTOR, 100, key
            )

            viewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, true)

            viewPagerAdapter?.addFragment(
                ChildPhotoFragment.newInstance(listPhoto), "Hình ảnh"
            )
            viewPagerAdapter?.addFragment(
                ChildPhotoFragment.newInstance(listIllustration), "Minh họa"
            )
            viewPagerAdapter?.addFragment(
                ChildPhotoFragment.newInstance(listVector), "Vectors"
            )

            vpSearchPhoto.adapter = viewPagerAdapter
            val limit = (if (viewPagerAdapter!!.count > 1) viewPagerAdapter!!.count - 1 else 1)
            vpSearchPhoto.offscreenPageLimit = limit
            tabLayoutSearchPhoto.setViewPager(vpSearchPhoto)
            aviSearchPhoto.hide()
        }

    }

    override fun onStop() {
        super.onStop()
        Helpers.keyboardUtil(context!!, tvSearchPhoto, false)

    }

}
