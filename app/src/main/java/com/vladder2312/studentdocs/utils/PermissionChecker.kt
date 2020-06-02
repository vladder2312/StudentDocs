package com.vladder2312.studentdocs.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionChecker {

    private const val CAMERA_REQUEST_CODE = 3
    private const val WRITE_STORAGE_REQUEST_CODE = 3

    fun checkCameraPermission(activity: Activity) : Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_REQUEST_CODE
                )
                return false
            }
        } else {
            return true
        }
    }

    fun checkGalleryPermission(activity: Activity) : Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    WRITE_STORAGE_REQUEST_CODE
                )
                false
            }
        } else {
            return true
        }
    }
}