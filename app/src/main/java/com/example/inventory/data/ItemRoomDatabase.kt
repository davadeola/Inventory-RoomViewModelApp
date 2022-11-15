package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//set exportSchema to false so
//as to not keep schema version history backups

@Database(version = 1, entities = [Item::class], exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase() {

    abstract fun itemDao():ItemDao

    companion object{

        //the value of a volatile variable will never be cached an all writes and reads will be done to and from the main memory thus ensuring changes to the INSTANCE by one thread are visible to other threads immediately
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        //race condition=> multiple threads working on the database resulting to two different databases
        //synchronized=> ensures only one thread of execution at a time hence the database can only be initialized once
        fun getDatabase(context:Context): ItemRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, ItemRoomDatabase::class.java, "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance


            }
        }
    }

}