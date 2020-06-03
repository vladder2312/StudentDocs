package com.vladder2312.studentdocs.ui.document

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R

class DocumentActivity : MvpAppCompatActivity(), DocumentView {

    @InjectPresenter
    lateinit var presenter: DocumentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
    }

}