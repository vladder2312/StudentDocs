package com.vladder2312.studentdocs.ui.options

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.ui.about_app.AboutAppActivity
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_app_button -> {
                startAboutAppActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(context != null) presenter.loadData(context!!.contentResolver)
        initListeners()
    }

    override fun initListeners() {
        options_make_copy.setOnClickListener {
            presenter.makeCopy()
        }
        options_load_copy.setOnClickListener {
            presenter.loadCopy()
        }
        options_delete_data.setOnClickListener {
            showDeleteDialog()
        }
    }

    override fun showUsage(memory: String, amount: Int) {
        options_used_memory.text = memory
        options_file_amount.text = amount.toString()
    }

    override fun showDeleteDialog() {
        AlertDialog.Builder(context)
            .setMessage("Вы действительно хотите удалить все данные?")
            .setPositiveButton("Да") { _: DialogInterface, _: Int ->
                presenter.deleteData()
            }
            .setNegativeButton("Нет") { _: DialogInterface, _: Int -> }
            .show()
    }

    override fun startAboutAppActivity() {
        startActivity(Intent(context, AboutAppActivity::class.java))
    }
}