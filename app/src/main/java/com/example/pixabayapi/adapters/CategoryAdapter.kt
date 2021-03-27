package com.example.pixabayapi.adapters

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pixabayapi.R
import com.example.pixabayapi.activities.SearchActivity
import com.example.pixabayapi.datas.model.Category
import com.example.pixabayapi.datas.model.VideoDetail
import com.example.pixabayapi.fragments.ShowVideoFragment
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_video.view.*
import java.util.*

class CategoryAdapter(
    private val datas: MutableList<Category>
) : RecyclerView.Adapter<CategoryAdapter.CatehoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatehoryViewHolder {
        return CatehoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CatehoryViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    inner class CatehoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun onBind(category: Category) {
            itemView.apply {
                tv_cate_title.text = category.title

                Collections.shuffle(category.thumbs)
                var thumb1 = category.thumbs[0]
                Glide.with(this).load(thumb1).into(iv_thumb_1)
                var thumb2 = category.thumbs[1]
                Glide.with(this).load(thumb2).into(iv_thumb_2)
                var thumb3 = category.thumbs[2]
                Glide.with(this).load(thumb3).into(iv_thumb_3)
                var thumb4 = category.thumbs[3]
                Glide.with(this).load(thumb4).into(iv_thumb_4)


                tv_cate_photo.setOnClickListener {
                    var intent = Intent(it.context, SearchActivity::class.java)
                    var bundle = Bundle()
                    bundle.putString("type", "photo")
                    bundle.putString("key", category.key)
                    bundle.putString("title", category.title)
                    intent.putExtra("data", bundle)
                    it.context.startActivity(intent)
                }

                tv_cate_video.setOnClickListener {
                    var intent = Intent(it.context, SearchActivity::class.java)
                    var bundle = Bundle()
                    bundle.putString("type", "video")
                    bundle.putString("key", category.key)
                    bundle.putString("title", category.title)
                    intent.putExtra("data", bundle)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}