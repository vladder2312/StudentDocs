package com.vladder2312.studentdocs.ui.photo

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.bumptech.glide.Glide
import com.vladder2312.studentdocs.R
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Активити просмотра фото
 */
class PhotoActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_photo)
        initViews()
    }

    private fun initViews() {
        Glide.with(photo_image)
            .load(intent.getStringExtra("uri"))
            .into(photo_image)
    }
}