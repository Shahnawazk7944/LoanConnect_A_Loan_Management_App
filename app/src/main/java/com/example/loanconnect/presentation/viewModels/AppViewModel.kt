package com.example.loanconnect.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.repository.AppRepository
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AppStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private var _appStates =
        MutableStateFlow(AppStates())
    val appStates = _appStates.asStateFlow()


    fun onEvent(event: AppEvents) {
        when (event) {
            is AppEvents.ApplyForLoan -> {
                applyForLoan(
                    LoanRequest(
                        mobile = event.contactNumber,
                        loan_amount = event.amount,
                        loan_duration = event.duration
                    )
                )
            }

            is AppEvents.ErrorShown -> {
                _appStates.update {
                    it.copy(showError = event.showError, error = event.removeError)
                }
            }
        }
    }

    private fun applyForLoan(loanRequest: LoanRequest) {
        _appStates.update {
            it.copy(applyingForLoan = true)
        }
        viewModelScope.launch {
            val result =
                appRepository.applyForLoan(loanRequest).onRight { successResponse ->
                    _appStates.update {
                        it.copy(
                            applyingForLoan = false,
                            appliedForLoanSuccessMessage = successResponse.message
                        )
                    }
                }.onLeft { failedResponse ->

                    _appStates.update {
                        it.copy(
                            applyingForLoan = false,
                            showError = true,
                            error = failedResponse.message
                        )
                    }

                }

        }

    }

}