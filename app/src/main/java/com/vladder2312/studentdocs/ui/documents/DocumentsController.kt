package com.vladder2312.studentdocs.ui.documents

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Document
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class DocumentsController(
    private val onClickListener: (Document) -> Unit
) : BindableItemController<Document, DocumentsController.DocumentHolder>() {

    inner class DocumentHolder(parent: ViewGroup) :
        BindableViewHolder<Document>(parent, R.layout.item_document) {
        private val card: CardView = itemView.findViewById(R.id.item_document_card)
        private val image: ImageView = itemView.findViewById(R.id.item_document_image)
        private val name: TextView = itemView.findViewById(R.id.item_document_name)
        private lateinit var document: Document

        init {
            card.setOnClickListener { onClickListener(document) }
        }

        override fun bind(data: Document) {
            this.document = data
            name.text = data.name
            Glide.with(image)
                .load(data.coverURI)
                .into(image)
        }
    }

    override fun getItemId(doc: Document) = doc.id

    override fun createViewHolder(parent: ViewGroup) = DocumentHolder(parent)
}