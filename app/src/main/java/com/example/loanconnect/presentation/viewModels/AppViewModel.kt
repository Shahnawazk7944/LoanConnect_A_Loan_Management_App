package com.example.loanconnect.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AppStates
import com.example.loanconnect.presentation.states.AuthEvents
import com.example.loanconnect.presentation.states.AuthStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(

) : ViewModel() {
    private var _appStates =
        MutableStateFlow(AppStates())
    val appStates = _appStates.asStateFlow()


    fun onEvent(event: AppEvents) {
        when (event) {
            is AppEvents.ApplyForLoan -> {

            }
        }
    }

    private fun applyForLoan(loanRequest: LoanRequest) {
        _appStates.update {
            it.copy(applyingForLoan = true)
        }
        viewModelScope.launch {
            val result =
                authRepository.signInRequest(signInRequest).onRight { authSuccessResponse ->
                    _appStates.update {
                        it.copy(loading = false, isLoggedIn = true)
                    }
                }.onLeft { authFailedResponse ->

                    _appStates.update {
                        it.copy(
                            loading = false,
                            showError = true,
                            error = authFailedResponse.message
                        )
                    }

                }

        }

    }

}