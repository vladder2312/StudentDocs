package com.vladder2312.studentdocs.ui.documents

import androidx.appcompat.widget.SearchView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
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
                model.documents.addAll(it)
                viewState.setData(model.documents)
            }
    }
}