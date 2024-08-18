package com.example.loanconnect.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.UpdateMobileNumberRequest
import com.example.loanconnect.domain.model.UpdateUsernameRequest
import com.example.loanconnect.domain.model.UploadPhotoRequest
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

            is AppEvents.UpdateNewMobileNumber -> {
                updateMobileNumber(
                    UpdateMobileNumberRequest(
                        mobile = event.mobileNumberAsId,
                        new_mobile = event.newData
                    )
                )
            }

            is AppEvents.UpdateNewUsername -> {
                updateNewUsername(
                    UpdateUsernameRequest(
                        mobile = event.mobileNumberAsId,
                        username = event.newData
                    )
                )
            }

            is AppEvents.UpdateImageUri -> {
                _appStates.update {
                    it.copy(imageUri = event.newUri)
                }
            }

            is AppEvents.UploadPhoto -> {
                uploadPhoto(
                    UploadPhotoRequest(
                        mobile = event.mobileNumberAsId, photo = event.base64String
                    )
                )
            }

            is AppEvents.SaveBitmap -> {
                _appStates.update {
                    it.copy(bitmap = event.bitmap)
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


    private fun updateNewUsername(updateUsernameRequest: UpdateUsernameRequest) {
        _appStates.update {
            it.copy(updatingNewUsername = true)
        }
        viewModelScope.launch {
            val result =
                appRepository.updateUsername(updateUsernameRequest).onRight { successResponse ->
                    _appStates.update {
                        it.copy(
                            updatingNewUsername = false,
                            updatedNewUsernameSuccessMessage = successResponse.message
                        )
                    }
                }.onLeft { failedResponse ->

                    _appStates.update {
                        it.copy(
                            updatingNewUsername = false,
                            showError = true,
                            error = failedResponse.message
                        )
                    }

                }

        }

    }

    private fun updateMobileNumber(updateMobileNumberRequest: UpdateMobileNumberRequest) {
        _appStates.update {
            it.copy(updatingNewMobileNumber = true)
        }
        viewModelScope.launch {
            val result =
                appRepository.updateMobileNumber(updateMobileNumberRequest)
                    .onRight { successResponse ->
                        _appStates.update {
                            it.copy(
                                updatingNewMobileNumber = false,
                                updatedNewMobileNumberSuccessMessage = successResponse.message
                            )
                        }
                    }.onLeft { failedResponse ->

                    _appStates.update {
                        it.copy(
                            updatingNewMobileNumber = false,
                            showError = true,
                            error = failedResponse.message
                        )
                    }

                }

        }

    }

    private fun uploadPhoto(uploadPhotoRequest: UploadPhotoRequest) {
        _appStates.update {
            it.copy(uploadingPhoto = true)
        }
        viewModelScope.launch {
            val result =
                appRepository.uploadPhoto(uploadPhotoRequest).onRight { successResponse ->
                    _appStates.update {
                        it.copy(
                            uploadingPhoto = false,
                            photoUploadedSuccessMessage = successResponse.message
                        )
                    }
                }.onLeft { failedResponse ->

                    _appStates.update {
                        it.copy(
                            uploadingPhoto = false,
                            showError = true,
                            error = failedResponse.message
                        )
                    }

                }

        }

    }

}