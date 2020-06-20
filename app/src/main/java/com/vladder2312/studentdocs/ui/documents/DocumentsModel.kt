package com.vladder2312.studentdocs.ui.documents

import com.vladder2312.studentdocs.domain.Document

/**
 * Модель фрагмента просмотра списка документов
 */
class DocumentsModel {

    var documents = mutableListOf<Document>()
    var page = 0
    var searchQueryText = ""
}