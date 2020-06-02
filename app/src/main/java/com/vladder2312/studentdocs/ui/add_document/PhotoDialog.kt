package com.vladder2312.studentdocs.ui.add_document

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.vladder2312.studentdocs.R
import kotlinx.android.synthetic.main.dialog_photo.*

object PhotoDialog {

    fun showDialog(context: Context, onCameraClick: () -> Unit, onGalleryClick: () -> Unit) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_photo)

        dialog.camera_action.setOnClickListener {
            onCameraClick()
            dialog.hide()
        }

        dialog.gallery_action.setOnClickListener {
            onGalleryClick()
            dialog.hide()
        }
        dialog.show()
    }
}