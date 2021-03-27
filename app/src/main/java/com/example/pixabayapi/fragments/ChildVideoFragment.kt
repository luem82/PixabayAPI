package com.example.pixabayapi.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.VideoAdapter
import com.example.pixabayapi.datas.model.VideoDetail
import kotlinx.android.synthetic.main.fragment_child_video.*
import java.io.Serializable

private const val ARG_PARAM1 = "param1"

class ChildVideoFragment : Fragment() {

    private var videos: MutableList<VideoDetail>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var videoAdapter: VideoAdapter? = null
    private var rcvVideo: RecyclerView? = null

    companion object {
        @JvmStatic
        fun newInstance(listVideo: MutableList<VideoDetail>) =
            ChildVideoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, listVideo as Serializable)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videos = it.getSerializable(ARG_PARAM1) as MutableList<VideoDetail>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcvVideo = view.findViewById(R.id.rcvVideo)

        gridLayoutManager = GridLayoutManager(context, 2)
        videoAdapter = VideoAdapter(videos!!)
        rcvVideo!!.layoutManager = gridLayoutManager
        rcvVideo!!.setHasFixedSize(true)
        rcvVideo!!.adapter = videoAdapter
    }

    fun clearVideo() {
        videos!!.clear()
        gridLayoutManager!!.removeAndRecycleAllViews(rcvVideo!!.Recycler())
        rcvVideo!!.removeAllViews()
        videoAdapter!!.notifyDataSetChanged()
    }
}
