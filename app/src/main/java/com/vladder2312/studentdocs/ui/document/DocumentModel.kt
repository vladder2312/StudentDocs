package com.vladder2312.studentdocs.ui.document

import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Photo

class DocumentModel {

    var name = ""
    var category = Category.Document
    var createdDate = 0
    var photos = mutableListOf<Photo>()
}