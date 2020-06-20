package com.vladder2312.studentdocs.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import java.io.File

/**
 * Утилита для создания ссылки для фото
 */
object UriCreator {

    fun createUri(context: Context): Uri {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val outFile = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "share_image_" + System.currentTimeMillis() + ".png"
        )
        return Uri.fromFile(outFile)
    }
}