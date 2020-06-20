package com.vladder2312.studentdocs.ui.documents

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.viewpager2.widget.ViewPager2
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.tabs.TabLayoutMediator
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.ui.document.DocumentActivity
import kotlinx.android.synthetic.main.fragment_documents.*
import ru.surfstudio.android.easyadapter.EasyAdapter

/**
 * Фрагмент просмотра и поиска документов
 */
class DocumentsFragment : MvpAppCompatFragment(), DocumentsView {

    @InjectPresenter
    lateinit var presenter: DocumentsPresenter
    private lateinit var search: SearchView
    private val documentAdapter = EasyAdapter()
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.documents_menu, menu)
        search = menu.getItem(0).actionView as SearchView
        search.setOnQueryTextListener(presenter.queryTextListener)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadDocuments()
    }

    override fun initViews() {
        documents_pager.adapter = DocumentsPagerAdapter(documentAdapter)
        documents_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter.pageSelected(position)
            }
        })
        TabLayoutMediator(
            document_tabs,
            documents_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = resources.getStringArray(R.array.tab_titles)[position]
            }).attach()
    }

    override fun setData(documents: List<Document>) {
        documentAdapter.setData(documents, documentController)
    }

    override fun startDocumentActivity(document: Document) {
        val intent = Intent(context, DocumentActivity::class.java)
        intent.putExtra("document", document)
        startActivity(intent)
    }
}