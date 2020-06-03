package com.vladder2312.studentdocs.domain

import com.vladder2312.studentdocs.data.db.entities.DocumentEntity
import java.io.Serializable

data class Document(
    val id: String,
    val name: String,
    val category: Category,
    val createdDate: Long
) : Serializable {

    fun transform(): DocumentEntity {
        val category : Int = when (category) {
            Category.Document -> {
                0
            }
            Category.Certificate -> {
                1
            }
            Category.Reward -> {
                2
            }
        }
        return DocumentEntity(id, name, category, createdDate)
    }
}