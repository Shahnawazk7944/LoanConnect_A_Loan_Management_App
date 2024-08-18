package com.example.loanconnect.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.UpdateMobileNumberRequest
import com.example.loanconnect.domain.model.UpdateUsernameRequest
import com.example.loanconnect.domain.model.UploadPhotoRequest
import com.example.loanconnect.domain.repository.AdminRepository
import com.example.loanconnect.domain.repository.AppRepository
import com.example.loanconnect.presentation.states.AdminEvents
import com.example.loanconnect.presentation.states.AdminStates
import com.example.loanconnect.presentation.states.AppEvents
import com.example.loanconnect.presentation.states.AppStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AdminViewModel @Inject constructor(
    private val adminRepository: AdminRepository
) : ViewModel() {
    private var _adminStates =
        MutableStateFlow(AdminStates())
    val adminStates = _adminStates.asStateFlow()

//    init {
//        getUsersData()
//    }

    fun onEvent(event: AdminEvents) {
        when (event) {
            is AdminEvents.ErrorShown -> TODO()
            is AdminEvents.GetUserData -> {
                getUsersData()
            }
        }
    }



    private fun getUsersData() {
        _adminStates.update {
            it.copy(loadingAdminData = true)
        }
        viewModelScope.launch {
            val result =
                adminRepository.getUsersData().onRight { successResponse ->
                        _adminStates.update {
                            it.copy(
                                loadingAdminData = false,
                                usersData = successResponse
                            )
                        }
                    }.onLeft { failedResponse ->

                        _adminStates.update {
                        it.copy(
                            loadingAdminData = false,
                            showError = true,
                            error = failedResponse.title
                        )
                    }

                }

        }

    }


}