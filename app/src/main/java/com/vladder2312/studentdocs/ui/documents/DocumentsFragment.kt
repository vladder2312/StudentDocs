package com.vladder2312.studentdocs.ui.documents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.ui.document.DocumentActivity
import kotlinx.android.synthetic.main.fragment_documents.*
import ru.surfstudio.android.easyadapter.EasyAdapter

class DocumentsFragment : MvpAppCompatFragment(), DocumentsView {

    @InjectPresenter
    lateinit var presenter: DocumentsPresenter
    private val adapter = EasyAdapter()
    private val documentController = DocumentsController {
        startDocumentActivity(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecycler()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadDocuments()
    }

    override fun initRecycler() {
        documents_recycler.layoutManager = LinearLayoutManager(context)
        documents_recycler.adapter = adapter
    }

    override fun setData(documents: List<Document>) {
        adapter.setData(documents, documentController)
    }

    override fun startDocumentActivity(document: Document) {
        val intent = Intent(context, DocumentActivity::class.java)
        intent.putExtra("document", document)
        startActivity(intent)
    }
}