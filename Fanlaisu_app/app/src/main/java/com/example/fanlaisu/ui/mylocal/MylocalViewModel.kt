package com.example.fanlaisu.ui.mylocal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MylocalViewModel : ViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "This is  mylocal  Fragment"
    }

    val text: LiveData<String> = _text
}