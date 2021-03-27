package com.example.pixabayapi.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.text.DecimalFormat

object Helpers {

    fun keyboardUtil(context: Context, view: View, isShow: Boolean) {
        var imm =
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (isShow) {
//            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            imm.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        } else {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun parseIntToString(views: Int): String {
        var df = DecimalFormat("#,###")
        if (views != null && views > 0) {
            return df.format(views)
        } else {
            return "0"
        }
    }

    fun cutTags(tags: String): MutableList<String> {
        var result = mutableListOf<String>()
        var tag1 = tags.substringBefore(",")
        var tag2 = tags.substringBeforeLast(",").substringAfterLast(",")
        var tag3 = tags.substringAfterLast(",")
        result.add(0, tag1)
        result.add(1, tag2)
        result.add(2, tag3)
        return result
    }

    fun downloadVideo(url: String, title: String, id: Int, context: Context) {
        var downloadUri = Uri.parse(url)
        var fileName = "${title}_${id}.mp4"
        val downloadRequest = DownloadManager.Request(downloadUri)
            .setMimeType("application/mp4")
            .setTitle(fileName)
            .setDescription("Đang tải video...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

//        downloadRequest.setShowRunningNotification(true)
//        downloadRequest.allowScanningByMediaScanner()

        val downloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(downloadRequest)
        Toast.makeText(context, "Đang tải xuống video.", Toast.LENGTH_SHORT).show()
    }

    fun downloadPhoto(url: String, name: String, id: Int, context: Context) {
        var downloadUri = Uri.parse(url)
        var fileName = ""
        if (url.contains("jpg")) {
            fileName = "${name}_${id}.jpg"
        } else if (url.contains("png")) {
            fileName = "${name}_${id}.png"
        }

        val downloadRequest = DownloadManager.Request(downloadUri)
            .setTitle(fileName)
            .setDescription("Đang tải ảnh...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val downloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(downloadRequest)
        Toast.makeText(context, "Đang tải xuống ảnh.", Toast.LENGTH_SHORT).show()
    }
}