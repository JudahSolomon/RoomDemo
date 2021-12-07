package com.example.roomdemo.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase :RoomDatabase(){

    abstract val subscriberDAO: SubscriberDAO


    // creating a singleton instance of the database class which can be used to call the
    //database anywhere in the App
    companion object{
        @Volatile// this annotation is used to make the database visible on other threads
        private var INSTANCE : SubscriberDatabase? = null
        fun getInstance(context:Context):SubscriberDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                }
                return instance
            }

        }

    }
}