package com.example.loanconnect.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loanconnect.presentation.states.AuthStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.loanconnect.presentation.states.AuthEvents


@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {
    var authStates by mutableStateOf(AuthStates())
        private set

    fun onEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.SignIn -> {

            }
            is AuthEvents.SignUp ->{

            }
        }
    }




}