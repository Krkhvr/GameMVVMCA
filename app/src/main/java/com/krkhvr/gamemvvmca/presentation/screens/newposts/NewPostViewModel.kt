package com.krkhvr.gamemvvmca.presentation.screens.newposts

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krkhvr.gamemvvmca.R
import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.usecases.auth.AuthUseCases
import com.krkhvr.gamemvvmca.domain.usecases.posts.PostsUseCases
import com.krkhvr.gamemvvmca.presentation.utils.ComposeFileProvider
import com.krkhvr.gamemvvmca.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authUseCases: AuthUseCases,
    private val postsUseCases: PostsUseCases
) : ViewModel() {

    var state by mutableStateOf(NewPostState())

    private var photoImage: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOBILE", R.drawable.icon_mobile),
    )

    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

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

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }

    fun createPost() = viewModelScope.launch {
        createPostResponse = Response.Loading
        createPostResponse = postsUseCases.createPost(
            post = Post(
                name = state.name,
                description = state.description,
                category = state.category,
                userId = authUseCases.getCurrentUser()?.uid ?: ""
            ),
            file = photoImage!!
        )
    }

    fun clearScreen() {
        state = state.copy(
            image = "",
            name = "",
            description = "",
            category = ""
        )
    }

}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)