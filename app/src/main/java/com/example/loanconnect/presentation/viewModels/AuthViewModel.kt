package com.example.loanconnect.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest
import com.example.loanconnect.domain.repository.AuthRepository
import com.example.loanconnect.presentation.states.AuthEvents
import com.example.loanconnect.presentation.states.AuthStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _authStates =
        MutableStateFlow(AuthStates())
    val authStates = _authStates.asStateFlow()


    fun onEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.SignIn -> {
                signIn(
                    SignInRequest(
                        username = event.username,
                        password = event.password,
                    )
                )
            }

            is AuthEvents.SignUp -> {
                signUp(
                    SignUpRequest(
                        username = event.username,
                        password = event.password,
                        mobile = event.contactNumber
                    )
                )
            }

            is AuthEvents.ErrorShown -> {
                _authStates.update {
                    it.copy(showError = event.showError, error = null)
                }
            }

            is AuthEvents.Logout -> {
                _authStates.update {
                    it.copy(isLoggedIn = event.logout)
                }
            }
        }
    }

    private fun signUp(signUpRequest: SignUpRequest) {
        _authStates.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            val result =
                authRepository.signUpRequest(signUpRequest).onRight { authSuccessResponse ->
                    _authStates.update {
                        it.copy(
                            loading = false,
                            isLoggedIn = true,
                            username = signUpRequest.username,
                            contactNumber = signUpRequest.mobile
                        )
                    }
                    Log.d("AuthViewModel", "Sign up Status<VM>: ${authSuccessResponse.message}")
                }.onLeft { authFailedResponse ->

                    _authStates.update {
                        it.copy(
                            loading = false,
                            showError = true,
                            error = authFailedResponse.message
                        )
                    }

                    Log.d(
                        "AuthViewModel",
                        "Show Error : ${authStates.value.showError} - error: ${authStates.value.error}"
                    )
                    Log.d("AuthViewModel", "Sign up Status: fa ${authStates.value.error}")
                }

            Log.d("AuthViewModel", "Just for test in VM-launch")
        }
        Log.d("AuthViewModel", "Just for test in outside VM-launch")

    }

    private fun signIn(signInRequest: SignInRequest) {
        _authStates.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            val result =
                authRepository.signInRequest(signInRequest).onRight { authSuccessResponse ->
                    _authStates.update {
                        it.copy(loading = false, isLoggedIn = true)
                    }
                    Log.d("AuthViewModel", "Sign up Status<VM>: ${authSuccessResponse.message}")
                }.onLeft { authFailedResponse ->

                    _authStates.update {
                        it.copy(
                            loading = false,
                            showError = true,
                            error = authFailedResponse.message
                        )
                    }

                    Log.d(
                        "AuthViewModel",
                        "Show Error : ${authStates.value.showError} - error: ${authStates.value.error}"
                    )
                    Log.d("AuthViewModel", "Sign up Status: fa ${authStates.value.error}")
                }

            Log.d("AuthViewModel", "Just for test in VM-launch")
        }
        Log.d("AuthViewModel", "Just for test in outside VM-launch")

    }
}