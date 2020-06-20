package com.vladder2312.studentdocs.utils

import android.content.ContentResolver
import android.net.Uri
import android.os.ParcelFileDescriptor

/**
 * Утилита для расчёта размера памяти, занимаемой фотографиями
 */
object MemoryChecker {

    fun calculateUsedMemory(
        contentResolver: ContentResolver,
        uris: MutableList<Uri>
    ): String {
        var size = 0
        var fileDescriptor: ParcelFileDescriptor?
        uris.removeAll(uris.filter {
            it.toString().contains("content://")
        })
        for (uri in uris) {
            fileDescriptor = contentResolver.openFileDescriptor(uri, "r")
            if (fileDescriptor != null) {
                size += fileDescriptor.statSize.toInt() / 1024
            }
            fileDescriptor?.close()
        }
        return if(size>1024) {
            size/=1024
            size.toString()+"МБ"
        } else size.toString()+"КБ"
    }
}