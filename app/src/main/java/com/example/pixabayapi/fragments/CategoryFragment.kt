package com.example.pixabayapi.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.pixabayapi.R
import com.example.pixabayapi.adapters.CategoryAdapter
import com.example.pixabayapi.datas.model.Category
import com.example.pixabayapi.utils.FetchData
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        CoroutineScope(Dispatchers.Main).launch {
//            FetchData.Categories()
//        }

        val linearLayoutManager = LinearLayoutManager(context)
        val categoryAdapter = CategoryAdapter(FetchData.getCategories())
        rcvCategor.apply {
            layoutManager = linearLayoutManager
            adapter = categoryAdapter
        }

    }

}
