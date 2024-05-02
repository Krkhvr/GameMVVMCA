package com.krkhvr.gamemvvmca.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.usecases.auth.AuthUseCases
import com.krkhvr.gamemvvmca.domain.usecases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
): ViewModel() {

    var state by mutableStateOf(SignupState())
    private set

    private var isUsernameValid by mutableStateOf(false)
    var usernameErrorMsg by mutableStateOf("")
        private set

    private var isEmailValid by mutableStateOf(false)
    var emailErrorMsg by mutableStateOf("")
        private set

    private var isPasswordValid by mutableStateOf(false)
    var passwordErrorMsg by mutableStateOf("")
        private set

    private var isConfirmPasswordValid by mutableStateOf(false)
    var confirmPasswordErrorMsg by mutableStateOf("")
        private set

    var isAccountValid by mutableStateOf(false)
        private set

    private val _signupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Response<FirebaseUser>?> = _signupFlow

    private val userAccount: User
        get() {
            return User(
                id = authUseCases.getCurrentUser()?.uid ?: "",
                username = state.username,
                email = state.email,
                password = state.password,
            )
        }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun validUsername(){
        if(state.username.length >= 5){
            isUsernameValid = true
            usernameErrorMsg = ""
        }else{
            isUsernameValid = false
            usernameErrorMsg = "Al menos 5 caracteres"
        }
        validateAccount()
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

    fun validateConfirmPassword(){
        if(state.password == state.confirmPassword){
            isConfirmPasswordValid = true
            confirmPasswordErrorMsg = ""
        }else{
            isConfirmPasswordValid = false
            confirmPasswordErrorMsg = "Las contraseñas no coinciden"
        }
        validateAccount()
    }

    private fun validateAccount(){
        isAccountValid =
            isUsernameValid && isEmailValid &&
                    isPasswordValid && isConfirmPasswordValid
    }

    fun signup() =
        viewModelScope.launch {
            _signupFlow.apply {
                value = Response.Loading
                value = authUseCases.signUp(userAccount)
            }
        }

    fun createUser() =
        viewModelScope.launch {
            usersUseCases.create(userAccount)
        }
}