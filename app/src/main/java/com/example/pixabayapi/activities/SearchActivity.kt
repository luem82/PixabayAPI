package com.example.pixabayapi.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.ViewPagerAdapter
import com.example.pixabayapi.fragments.ChildPhotoFragment
import com.example.pixabayapi.fragments.ChildVideoFragment
import com.example.pixabayapi.utils.Consts
import com.example.pixabayapi.utils.FetchData
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(tool_bar_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var bundle = intent.getBundleExtra("data")
        var type = bundle!!.getString("type")
        var key = bundle!!.getString("key")
        var title = bundle!!.getString("title")

        initTitleToolBar(type, title)

        if (type.equals("photo")) {
            initPhotos(key!!)
        } else {
            initVideos(key!!)
        }

    }

    private fun initVideos(key: String) {

        CoroutineScope(Dispatchers.Main).launch {

            var listVideoFilm = FetchData.searchVideos(
                Consts.VIDEO_TYPE_FILM, 200, key
            )
            var listVideoAnim = FetchData.searchVideos(
                Consts.VIDEO_TYPE_ANIM, 200, key
            )

            var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, false)

            viewPagerAdapter?.addFragment(
                ChildVideoFragment.newInstance(listVideoFilm), "Film"
            )
            viewPagerAdapter?.addFragment(
                ChildVideoFragment.newInstance(listVideoAnim), "Animation"
            )

            vpSearch.adapter = viewPagerAdapter
            val limit = (if (viewPagerAdapter!!.count > 1) viewPagerAdapter!!.count - 1 else 1)
            vpSearch.offscreenPageLimit = limit
            tabLayoutSearch.setViewPager(vpSearch)
            aviSearch.hide()
        }

    }

    private fun initPhotos(key: String) {

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

            var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, true)

            viewPagerAdapter?.addFragment(
                ChildPhotoFragment.newInstance(listPhoto), "Hình ảnh"
            )
            viewPagerAdapter?.addFragment(
                ChildPhotoFragment.newInstance(listIllustration), "Minh họa"
            )
            viewPagerAdapter?.addFragment(
                ChildPhotoFragment.newInstance(listVector), "Vectors"
            )

            vpSearch.adapter = viewPagerAdapter
            val limit = (if (viewPagerAdapter!!.count > 1) viewPagerAdapter!!.count - 1 else 1)
            vpSearch.offscreenPageLimit = limit
            tabLayoutSearch.setViewPager(vpSearch)
            aviSearch.hide()
        }

    }


    private fun initTitleToolBar(type: String?, title: String?) {
        when (type) {
            "photo" -> supportActionBar?.title = "Hình Ảnh: ${title}"
            else -> supportActionBar?.title = "Video: ${title}"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
