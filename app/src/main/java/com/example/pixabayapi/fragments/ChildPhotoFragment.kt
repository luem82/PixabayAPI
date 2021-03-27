package com.example.pixabayapi.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.PhotoAdapter
import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.utils.FetchData
import kotlinx.android.synthetic.main.fragment_child_photo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChildPhotoFragment : Fragment() {

    private var photos: MutableList<PhotoDetail>? = null
    private var photoAdapter: PhotoAdapter? = null
    private var staggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    private var rcvPhoto: RecyclerView? = null

    companion object {
        @JvmStatic
        fun newInstance(listPhoto: MutableList<PhotoDetail>) =
            ChildPhotoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, listPhoto as Serializable)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photos = it.getSerializable(ARG_PARAM1) as MutableList<PhotoDetail>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcvPhoto = view.findViewById(R.id.rcvPhoto)

        staggeredGridLayoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
        photoAdapter = PhotoAdapter(photos!!)
        rcvPhoto!!.layoutManager = staggeredGridLayoutManager
        rcvPhoto!!.adapter = photoAdapter

    }

    fun clearPhotos() {
        photos!!.clear()
        staggeredGridLayoutManager!!.removeAndRecycleAllViews(rcvPhoto!!.Recycler())
        rcvPhoto!!.removeAllViews()
        photoAdapter!!.notifyDataSetChanged()
    }

}
