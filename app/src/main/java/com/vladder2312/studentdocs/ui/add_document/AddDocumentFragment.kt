package com.vladder2312.studentdocs.ui.add_document

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R

class AddDocumentFragment : MvpAppCompatFragment(), AddDocumentView {

    @InjectPresenter
    lateinit var presenter: AddDocumentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_document, container, false)
    }
}