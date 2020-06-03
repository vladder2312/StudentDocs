package com.vladder2312.studentdocs.ui.photo

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.squareup.picasso.Picasso
import com.vladder2312.studentdocs.R
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : MvpAppCompatActivity(), PhotoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_photo)
        initViews()
    }

    override fun initViews(){
        Picasso.get().load(intent.getStringExtra("uri")).into(photo_image)
    }
}