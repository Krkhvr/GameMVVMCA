package com.krkhvr.gamemvvmca.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.usecases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private var isEmailValid by mutableStateOf(false)
    var emailErrorMsg by mutableStateOf("")
        private set

    private var isPasswordValid by mutableStateOf(false)
    var passwordErrorMsg by mutableStateOf("")
        private set

    var isAccountValid by mutableStateOf(false)
        private set

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    private val currentUser = authUseCases.getCurrentUser()

    init {
        if(currentUser != null){
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrorMsg = ""
        } else {
            isEmailValid = false
            emailErrorMsg = "El email no es válido"
        }
        validateAccount()
    }

    fun validatePassword(){
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrorMsg = ""
        } else {
            isPasswordValid = false
            passwordErrorMsg = "Al menos 6 caracteres"
        }
        validateAccount()
    }

    private fun validateAccount(){
        isAccountValid =  isEmailValid && isPasswordValid
    }

    fun login() =
        viewModelScope.launch {
            loginResponse =  Response.Loading
            loginResponse =  authUseCases.login(state.email, state.password)
        }
}