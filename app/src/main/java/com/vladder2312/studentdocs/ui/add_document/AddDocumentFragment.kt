package com.vladder2312.studentdocs.ui.add_document

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class AddDocumentFragment : MvpAppCompatFragment() {

    @InjectPresenter
    lateinit var presenter: AddDocumentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}