package com.example.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.DataBase.Subscriber
import com.example.roomdemo.DataBase.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository): ViewModel() {

    val subscriber = repository.subscribers

    // MutableLiveData variables for Name and Email fields
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    //MutableLiveData variables for the buttons
    val clearAllOrDeleteButton = MutableLiveData<String>()
    val saveOrUpdateButton = MutableLiveData<String>()

    //running an init block here so that the text on the buttons will
    // update based on the user interaction
    init {
        clearAllOrDeleteButton.value = "Clear"
        saveOrUpdateButton.value = "Save"
    }

    fun saveOrUpdate(){
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subscriber(0, name, email))
        inputName.value = null
        inputEmail.value = null

    }

    fun clearAllOrDelete(){
        clearAll()

    }
    // creating and running the insert, update and clearAll functions  on the background thread
    private fun insert(subscriber: Subscriber):Job = viewModelScope.launch{
            repository.insert(subscriber)
        }

    fun update(subscriber: Subscriber){
        viewModelScope.launch {
            repository.update(subscriber)
        }
    }
    fun delete(subscriber: Subscriber){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
    fun clearAll():Job = viewModelScope.launch {
            repository.deleteAll()
        }
    }

