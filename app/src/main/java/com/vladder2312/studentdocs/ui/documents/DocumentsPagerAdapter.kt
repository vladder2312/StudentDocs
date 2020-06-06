package com.vladder2312.studentdocs.ui.documents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vladder2312.studentdocs.R
import kotlinx.android.synthetic.main.page_documents.view.*
import ru.surfstudio.android.easyadapter.EasyAdapter

class DocumentsPagerAdapter(
    private val adapter: EasyAdapter
) : RecyclerView.Adapter<DocumentsPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.page_documents, parent, false)
        )

    override fun getItemCount() = 4

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) = holder.itemView.run {
        documents_recycler.layoutManager = LinearLayoutManager(context)
        documents_recycler.adapter = adapter
    }

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}