package com.pinksoft.pinksoft.network.model

import androidx.room.Entity

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val company: Company,
    val phone: String,
    val website: String
)