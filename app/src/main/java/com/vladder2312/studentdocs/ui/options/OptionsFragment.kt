package com.vladder2312.studentdocs.ui.options

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import kotlinx.android.synthetic.main.fragment_options.*

class OptionsFragment : MvpAppCompatFragment(), OptionsView {

    @InjectPresenter
    lateinit var presenter: OptionsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(context != null) presenter.loadData(context!!.contentResolver)
        initListeners()
    }

    override fun initListeners() {

    }

    override fun showUsage(memory: String, amount: Int) {
        options_used_memory.text = memory
        options_file_amount.text = amount.toString()
    }
}