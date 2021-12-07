package com.example.roomdemo.DataBase


// creating an instance of the subscriberDao class in other to call the SubscriberDao
//funs in the class
class SubscriberRepository(val dao: SubscriberDAO) {
    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }
    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }
    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }
   suspend fun deleteAll(){
        dao.deleteAll()
    }
}