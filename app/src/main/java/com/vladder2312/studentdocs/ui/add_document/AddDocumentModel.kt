package com.vladder2312.studentdocs.ui.add_document

import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Photo

/**
 * Модель создания документа
 */
class AddDocumentModel {

    var id = System.currentTimeMillis().toString()
    var photos = mutableListOf<Photo>()
    var name = ""
    var category = Category.Document
}