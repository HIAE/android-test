package com.araujoraul.aechuck.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.araujoraul.aechuck.BuildConfig
import java.util.*

class MainViewModel : ViewModel() {

    private val _year = MutableLiveData<Int>().apply {
        value = Calendar.getInstance().get(Calendar.YEAR)
    }

    private val _version = MutableLiveData<String>().apply {
        value = BuildConfig.VERSION_NAME
    }

    val year: LiveData<Int> = _year
    val version: LiveData<String> = _version

}