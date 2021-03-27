package com.example.pixabayapi.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.pixabayapi.R
import com.example.pixabayapi.utils.Consts
import com.example.pixabayapi.utils.FetchData
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkInternetFetchDatas()
    }

    private fun checkInternetFetchDatas() {

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager!!.activeNetworkInfo

        if (networkInfo == null || !networkInfo.isConnected || !networkInfo.isAvailable) {
            // when internet is inactive
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.check_internet)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = android.R.style.Animation_Dialog

            val btnTryAgain = dialog.findViewById<Button>(R.id.btn_try_again)
            btnTryAgain.setOnClickListener {
                //call recreeate()
                recreate()
                dialog.dismiss()
            }

            dialog.show()
        } else {
            // when internet is active
            // fetch data
            CoroutineScope(Dispatchers.Main).launch {

                val keyPrimary = Consts.LIST_CATE_KEY.random().substringBefore("/")

                pbSplash.progress = 2
                Consts.LIST_PHOTO_PHOTO.addAll(
                    FetchData.searchPhotos(Consts.PHOTO_TYPE_PHOTO, 30, keyPrimary)
                )

                pbSplash.progress = 4
                Consts.LIST_PHOTO_ILLUS.addAll(
                    FetchData.searchPhotos(Consts.PHOTO_TYPE_ILLUS, 30, keyPrimary)
                )
                pbSplash.progress = 6
                Consts.LIST_PHOTO_VECTOR.addAll(
                    FetchData.searchPhotos(Consts.PHOTO_TYPE_VECTOR, 30, keyPrimary)
                )
                pbSplash.progress = 8
                Consts.LIST_VIDEO_FILM.addAll(
                    FetchData.searchVideos(Consts.VIDEO_TYPE_FILM, 30, keyPrimary)
                )
                pbSplash.progress = 10
                Consts.LIST_VIDEO_ANIM.addAll(
                    FetchData.searchVideos(Consts.VIDEO_TYPE_ANIM, 30, keyPrimary)
                )

                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }
        }
    }

}
