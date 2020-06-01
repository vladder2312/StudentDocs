package com.vladder2312.studentdocs.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladder2312.studentdocs.domain.Document

@Entity
data class DocumentEntity(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "createdDate") var createdDate : Long
) {

    fun transform() : Document{
        return Document(id, name, category, createdDate)
    }
}