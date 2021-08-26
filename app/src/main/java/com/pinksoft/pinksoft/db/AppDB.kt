package com.pinksoft.pinksoft.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pinksoft.pinksoft.network.model.Posts
import com.pinksoft.pinksoft.network.model.TypeConverterOwner


@Database(entities = [Posts::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterOwner::class)
abstract class AppDB : RoomDatabase(){

    abstract fun getDAO():DAO
    companion object{
        private  var  DB_INSTANSE: AppDB? = null
        fun getAppDBInstance(context: Context) : AppDB{
            if (DB_INSTANSE == null){
                DB_INSTANSE = Room.databaseBuilder(context.applicationContext, AppDB::class.java,"AppDataBase")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANSE!!
        }
    }
}





