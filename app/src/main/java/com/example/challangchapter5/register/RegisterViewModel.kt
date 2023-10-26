package com.example.challangchapter5.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class RegisterViewModel : ViewModel() {
    private var _Register = MutableLiveData<String>()
    val register: LiveData<String> = _Register

    fun registerFirebase(email: String, password: String) =
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _Register.postValue("Register Success!")
            } else {
                _Register.postValue(it.exception.toString())
            }
        }
}