package com.vladder2312.studentdocs.ui.add_document

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import kotlinx.android.synthetic.main.fragment_add_document.*
import ru.surfstudio.android.easyadapter.EasyAdapter

class AddDocumentFragment : MvpAppCompatFragment(), AddDocumentView {

    @InjectPresenter
    lateinit var presenter: AddDocumentPresenter
    private val adapter = EasyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_add_document, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecycler()
        initListeners()
        initViews()
    }

    override fun initRecycler() {
        foto_recycler.layoutManager =
            GridLayoutManager(foto_recycler.context, 1, GridLayoutManager.HORIZONTAL, false)
        foto_recycler.adapter = adapter
    }

    override fun initListeners() {
        add_doc_add_photo.setOnClickListener { _ ->
            context?.let { it ->
                PhotoDialog.showDialog(it, this::openCamera, this::openGallery)
            }
        }
    }

    override fun initViews() {
        val categories = resources.getStringArray(R.array.categories)
        val adapter: ArrayAdapter<String>
        if (context != null) {
            adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, categories)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            add_doc_category.adapter = adapter
            add_doc_category.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        presenter.categorySelected(position)
                    }
                }
        }
    }

    override fun openCamera() {

    }

    override fun openGallery() {

    }
}