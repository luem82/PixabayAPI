package com.example.pixabayapi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.pixabayapi.fragments.ChildPhotoFragment
import com.example.pixabayapi.fragments.ChildVideoFragment
import java.io.Serializable

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    var isPhoto: Boolean
) : FragmentStatePagerAdapter(fragmentManager), Serializable {

    var listFragment: ArrayList<Fragment>
    var listTitle: ArrayList<String>

    init {
        listFragment = arrayListOf()
        listTitle = arrayListOf()
    }

    fun cleanUp() {

        if (isPhoto) {
            (listFragment[0] as ChildPhotoFragment).clearPhotos()
            (listFragment[1] as ChildPhotoFragment).clearPhotos()
            (listFragment[2] as ChildPhotoFragment).clearPhotos()
        } else {
            (listFragment[0] as ChildVideoFragment).clearVideo()
            (listFragment[1] as ChildVideoFragment).clearVideo()
        }
        listFragment.clear()
        listTitle.clear()
        notifyDataSetChanged()
    }


    fun addFragment(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    override fun getItem(position: Int): Fragment = listFragment[position]

    override fun getCount(): Int = listFragment.size

    override fun getPageTitle(position: Int): CharSequence? = listTitle[position]
}