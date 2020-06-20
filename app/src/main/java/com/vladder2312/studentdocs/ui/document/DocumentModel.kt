package com.vladder2312.studentdocs.ui.document

import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Photo

/**
 * Модель с информацией о документе
 */
class DocumentModel {

    var id = ""
    var name = ""
    var category = Category.Document
    var createdDate = 0L
    var photos = mutableListOf<Photo>()
}