package com.example.pixabayapi.fragments


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.ViewPagerAdapter
import com.example.pixabayapi.utils.Consts
import com.example.pixabayapi.utils.FetchData
import com.example.pixabayapi.utils.Helpers
import kotlinx.android.synthetic.main.fragment_search_photo.*
import kotlinx.android.synthetic.main.fragment_search_video.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchVideoFragment : Fragment() {

    var viewPagerAdapter: ViewPagerAdapter? = null
    lateinit var keySearch: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Helpers.keyboardUtil(context!!, edtSearchVideo, true)

        tvSearchVideo.setOnClickListener {
            if (TextUtils.isEmpty(edtSearchVideo.text.toString())) {
                Toast.makeText(context, "Nhập từ khóa video cần tìm", Toast.LENGTH_SHORT).show()
            } else {
                keySearch = edtSearchVideo.text.toString().replace(" ", "+")
                searchVideo(keySearch)
                aviSearchVidep.show()
                Helpers.keyboardUtil(context!!, it, false)
            }
        }

    }

    private fun searchVideo(key: String) {

        if (viewPagerAdapter != null) {
            vpSearchVideo.removeAllViews()
            viewPagerAdapter!!.cleanUp()
        }

        CoroutineScope(Dispatchers.Main).launch {

            var listVideoFilm = FetchData.searchVideos(
                Consts.VIDEO_TYPE_FILM, 200, key
            )
            var listVideoAnim = FetchData.searchVideos(
                Consts.VIDEO_TYPE_ANIM, 200, key
            )

            viewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, false)

            viewPagerAdapter?.addFragment(
                ChildVideoFragment.newInstance(listVideoFilm), "Film"
            )
            viewPagerAdapter?.addFragment(
                ChildVideoFragment.newInstance(listVideoAnim), "Animation"
            )

            vpSearchVideo.adapter = viewPagerAdapter
            val limit = (if (viewPagerAdapter!!.count > 1) viewPagerAdapter!!.count - 1 else 1)
            vpSearchVideo.offscreenPageLimit = limit
            tabLayoutSearchVideo.setViewPager(vpSearchVideo)
            aviSearchVidep.hide()
        }

    }


    override fun onStop() {
        super.onStop()
        Helpers.keyboardUtil(context!!, tvSearchVideo, false)
    }

}
