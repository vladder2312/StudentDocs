package com.vladder2312.studentdocs.ui.add_document

import com.vladder2312.studentdocs.domain.Category

class AddDocumentModel {

    var photoUTLs = mutableListOf<String>()
    var name = ""
    var category = Category.Document
}