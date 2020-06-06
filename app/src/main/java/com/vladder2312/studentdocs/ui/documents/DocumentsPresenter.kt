package com.vladder2312.studentdocs.ui.documents

import androidx.appcompat.widget.SearchView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.domain.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class DocumentsPresenter : MvpPresenter<DocumentsView>() {

    @Inject
    lateinit var docRepository: DocRepository
    val model = DocumentsModel()
    val queryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?) = true

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText != null) {
                viewState.setData(model.documents.filter {
                    it.name.toLowerCase(Locale.getDefault())
                        .contains(newText.trim().toLowerCase(Locale.getDefault()))
                })
            }
            return true
        }
    }

    init {
        App.appComponent.inject(this)
    }

    fun loadDocuments() {
        val disposable = docRepository.getDocuments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                model.documents.clear()
                model.documents.addAll(it)
                viewState.setData(model.documents)
            }
    }

    fun filterDocuments(category: Int) {
        when (category) {
            0 -> viewState.setData(model.documents)
            1 -> viewState.setData(model.documents.filter {
                it.category == Category.Document
            })
            2 -> viewState.setData(model.documents.filter {
                it.category == Category.Certificate
            })
            3 -> viewState.setData(model.documents.filter {
                it.category == Category.Reward
            })
        }
    }
}