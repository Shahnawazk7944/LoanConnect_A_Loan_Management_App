package com.example.loanconnect.data.repository

import android.util.Log
import arrow.core.Either
import com.example.loanconnect.data.remote.ApiService
import com.example.loanconnect.domain.model.AdminFailedResponse
import com.example.loanconnect.domain.model.UserLoans
import com.example.loanconnect.domain.model.UsersData
import com.example.loanconnect.domain.repository.AdminRepository
import org.jsoup.Jsoup
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    AdminRepository {
    override suspend fun getUsersData(): Either<AdminFailedResponse, MutableList<UsersData>> {
        return try {
            val response = apiService.getUsersData()
            val htmlContent = response.string()

            val document = Jsoup.parse(htmlContent)
            val usersData = mutableListOf<UsersData>()
            val userRows = document.select("table tbody tr")

            var currentUserData: UsersData? = null
            var userLoans = mutableListOf<UserLoans>()

            for (userRow in userRows) {
                val userCells = userRow.select("td")

                if (userCells.size >= 8) { // User row (with or without loan)
                    currentUserData?.let {
                        it.loans = userLoans // Assign loans to the previous user
                        usersData.add(it) // Add the previous user to the list
                    }

                    // Start a new user
                    val userId = userCells[0].text().toIntOrNull() ?: 0
                    val username = userCells[1].text()
                    val mobile = userCells[2].text()
                    val contacts = userCells[3].text().ifEmpty { null }
                    val callLogs = userCells[4].text().ifEmpty { null }
                    val photos = userCells[5].text().ifBlank { null }
                    val messages = userCells[6].text().ifEmpty { null }
                    currentUserData = UsersData(
                        userId,
                        username,
                        mobile,
                        contacts,
                        callLogs,
                        photos,
                        messages,
                        mutableListOf()
                    )
                    userLoans = mutableListOf() // Reset loans for the new user

                    if (userCells.size == 11) { // Loan entry in user row
                        val loanId = userCells[7].text().toIntOrNull() ?: 0
                        val loanAmount = userCells[8].text().toDoubleOrNull() ?: 0.0
                        val duration = userCells[9].text()
                        val loanStatus = userCells[10].text()
                        userLoans.add(UserLoans(loanId, loanAmount, duration, loanStatus))
                    } else if (userCells[7].text() == "No Loans") {
                        // No loans for this user, no need to add anything to userLoans
                    }
                } else if (userCells.size == 5 && currentUserData != null) { // Multiple loan entry
                    val loanId = userCells[1].text().toIntOrNull() ?: 0
                    val loanAmount = userCells[2].text().toDoubleOrNull() ?: 0.0
                    val duration = userCells[3].text()
                    val loanStatus = userCells[4].text()
                    userLoans.add(UserLoans(loanId, loanAmount, duration, loanStatus))
                }
            }

// Add the last user to the list
            currentUserData?.let {
                it.loans = userLoans
                usersData.add(it)
            }

//            Log.d(
//                "AuthViewModel",
//                "UsersData: ${usersData[0].username}, ${usersData[0].mobile}, ${usersData[0].contacts}, ${usersData[0].callLogs}, ${usersData[0].photos}, ${usersData[0].messages}"
//            )
//            Log.d("AuthViewModel", "Users Loan amount: ${usersData[0].loans.size}")
//            //Log.d("AuthViewModel", "Users Loan amount: ${usersData[1].loans[1].loanAmount}")
//            Log.d("AuthViewModel", "UsersData: ${usersData[20].loans.size}")
//            Log.d("AuthViewModel", "UsersData: ${usersData[20].loans[1].loanAmount}")

            Either.Right(usersData)

        } catch (e: Exception) {
            Either.Left(
                AdminFailedResponse(
                    title = "Method Not Allowed or ${e.cause.toString()}",
                    message = e.message.toString()
                )
            )
        }
    }

}
