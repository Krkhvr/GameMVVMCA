package com.krkhvr.gamemvvmca.domain.usecases.users

import com.krkhvr.gamemvvmca.domain.repository.UsersRepository
import java.io.File
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke(file: File) = repository.saveImage(file)
}