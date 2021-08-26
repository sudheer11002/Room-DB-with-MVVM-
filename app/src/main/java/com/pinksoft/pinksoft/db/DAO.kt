package com.pinksoft.pinksoft.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pinksoft.pinksoft.network.model.Posts
import com.pinksoft.pinksoft.network.model.User

@Dao
interface DAO {
    @Query("SELECT * FROM posts")
    fun  getAllPostsRecords(): LiveData<List<Posts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostRecords(posts: Posts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPost(posts: Posts)

    @Query("DELETE FROM posts")
    fun deleteAllRecords()


}