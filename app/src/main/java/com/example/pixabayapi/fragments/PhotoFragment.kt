package com.example.pixabayapi.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.ViewPagerAdapter
import com.example.pixabayapi.utils.Consts
import com.ogaclejapan.smarttablayout.SmartTabLayout
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, true)

        viewPagerAdapter.addFragment(
            ChildPhotoFragment.newInstance(Consts.LIST_PHOTO_PHOTO), "Hình ảnh"
        )
        viewPagerAdapter.addFragment(
            ChildPhotoFragment.newInstance(Consts.LIST_PHOTO_ILLUS), "Minh họa"
        )
        viewPagerAdapter.addFragment(
            ChildPhotoFragment.newInstance(Consts.LIST_PHOTO_VECTOR), "Vectors"
        )

        viewPagerPhoto.adapter = viewPagerAdapter
        val limit = (if (viewPagerAdapter.count > 1) viewPagerAdapter.count - 1 else 1)
        viewPagerPhoto.offscreenPageLimit = limit
        tabLayoutPhoto.setViewPager(viewPagerPhoto)

    }

}
