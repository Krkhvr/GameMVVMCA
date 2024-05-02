package com.krkhvr.gamemvvmca.domain.repository

import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>

    suspend fun update(user: User): Response<Boolean>

    suspend fun saveImage(file: File): Response<String>

    fun getUserById(id: String): Flow<User>
}