package com.example.pixabayapi.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.ViewPagerAdapter
import com.example.pixabayapi.utils.Consts
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, false)

        viewPagerAdapter.addFragment(
            ChildVideoFragment.newInstance(Consts.LIST_VIDEO_FILM), "Film"
        )
        viewPagerAdapter.addFragment(
            ChildVideoFragment.newInstance(Consts.LIST_VIDEO_ANIM), "Animation"
        )


        viewPagerVideo.adapter = viewPagerAdapter
        val limit = (if (viewPagerAdapter.count > 1) viewPagerAdapter.count - 1 else 1)
        viewPagerVideo.offscreenPageLimit = limit
        tabLayoutVideo.setViewPager(viewPagerVideo)

    }

}
