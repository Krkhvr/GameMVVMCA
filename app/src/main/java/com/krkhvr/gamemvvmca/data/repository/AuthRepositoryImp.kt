package com.krkhvr.gamemvvmca.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override  suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            Response.Success(
                firebaseAuth.signInWithEmailAndPassword(email, password).await().user!!
            )
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun signUp(user: User): Response<FirebaseUser> {
        return try {
            Response.Success(
                firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await().user!!
            )
        }catch (e: Exception){
            Response.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}