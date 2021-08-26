package com.pinksoft.pinksoft.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class Posts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids") val ids: Int = 0,
    @ColumnInfo(name = "body")val body: String,
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "userId")val userId: Int


)