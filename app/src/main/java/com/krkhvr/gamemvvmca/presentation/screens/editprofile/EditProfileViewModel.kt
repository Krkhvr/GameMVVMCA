package com.krkhvr.gamemvvmca.presentation.screens.editprofile

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.usecases.users.UsersUseCases
import com.krkhvr.gamemvvmca.presentation.utils.ComposeFileProvider
import com.krkhvr.gamemvvmca.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
): ViewModel() {

    var state by mutableStateOf(EditProfileState())
        private set

    val user: User =  User.fromJson(savedStateHandle.get<String>("user")!!)

    val resultingActivityHandler = ResultingActivityHandler()

    var usernameErrorMsg by mutableStateOf("")
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    private var photoImage: File? = null

    init {
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    fun pickImage() = viewModelScope.launch {
        resultingActivityHandler.getContent("image/*").run {
            this?.let {
                photoImage = ComposeFileProvider.createFileFromUri(context, it)
                onImageInput(it.toString())
            }
        }
    }

    fun takePhoto() = viewModelScope.launch {
        resultingActivityHandler.takePicturePreview().run {
            this?.let {
                onImageInput(ComposeFileProvider.getPathFromBitmap(context, it))
                photoImage = File(state.image)
            }
        }
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)

    }

    fun onImageInput(url: String){
        state = state.copy(image = url)
    }

    fun validUsername(){
        usernameErrorMsg = if(state.username.length >= 5){
            ""
        }else{
            "Al menos 5 caracteres"
        }
    }

    fun saveImage() = viewModelScope.launch {
        if(photoImage != null){
            saveImageResponse = Response.Loading
            saveImageResponse = usersUseCases.saveImage(photoImage!!)
        }
    }

    fun updateUsername() = viewModelScope.launch {
        updateResponse = Response.Loading
        updateResponse = usersUseCases.update(
            User(
                id = user.id,
                username = state.username,
                image = state.image,
            )
        )
    }
}