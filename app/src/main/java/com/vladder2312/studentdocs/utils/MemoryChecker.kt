package com.vladder2312.studentdocs.utils

import android.content.ContentResolver
import android.net.Uri
import android.os.ParcelFileDescriptor

object MemoryChecker {

    fun calculateUsedMemory(
        contentResolver: ContentResolver,
        uris: MutableList<Uri>
    ): Int {
        var memorySize = 0
        var fileDescriptor: ParcelFileDescriptor?
        uris.removeAll(uris.filter {
            it.toString().contains("content://")
        })
        for (uri in uris) {
            fileDescriptor = contentResolver.openFileDescriptor(uri, "r")
            if (fileDescriptor != null) {
                memorySize += fileDescriptor.statSize.toInt() / 1024
            }
            fileDescriptor?.close()
        }
        return memorySize
    }
}