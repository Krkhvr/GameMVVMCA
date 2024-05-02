package com.krkhvr.gamemvvmca.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.krkhvr.gamemvvmca.core.Constants.POSTS
import com.krkhvr.gamemvvmca.core.Constants.USERS
import com.krkhvr.gamemvvmca.data.repository.AuthRepositoryImp
import com.krkhvr.gamemvvmca.data.repository.PostRepositoryImp
import com.krkhvr.gamemvvmca.data.repository.UsersRepositoryImpl
import com.krkhvr.gamemvvmca.domain.repository.AuthRepository
import com.krkhvr.gamemvvmca.domain.repository.PostRepository
import com.krkhvr.gamemvvmca.domain.repository.UsersRepository
import com.krkhvr.gamemvvmca.domain.usecases.auth.AuthUseCases
import com.krkhvr.gamemvvmca.domain.usecases.auth.GetCurrentUserUseCase
import com.krkhvr.gamemvvmca.domain.usecases.auth.LoginUseCase
import com.krkhvr.gamemvvmca.domain.usecases.auth.LogoutUseCase
import com.krkhvr.gamemvvmca.domain.usecases.auth.SignUpUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.CreatePostUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.DeletePostUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.DislikePostUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.GetPostsByUserIdUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.GetPostsUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.LikePostUseCase
import com.krkhvr.gamemvvmca.domain.usecases.posts.PostsUseCases
import com.krkhvr.gamemvvmca.domain.usecases.posts.UpdatePostUseCase
import com.krkhvr.gamemvvmca.domain.usecases.users.CreateUseCase
import com.krkhvr.gamemvvmca.domain.usecases.users.GetUserByIdUseCase
import com.krkhvr.gamemvvmca.domain.usecases.users.SaveImageUseCase
import com.krkhvr.gamemvvmca.domain.usecases.users.UpdateUseCase
import com.krkhvr.gamemvvmca.domain.usecases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(firebaseStorage: FirebaseStorage): StorageReference =
        firebaseStorage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(POSTS)
    fun provideStoragePostsRef(firebaseStorage: FirebaseStorage): StorageReference =
        firebaseStorage.reference.child(POSTS)

    @Provides
    @Named(POSTS)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(imp: AuthRepositoryImp): AuthRepository = imp

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providePostsRepository(impl: PostRepositoryImp): PostRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) =
        AuthUseCases(
            getCurrentUser = GetCurrentUserUseCase(repository),
            login = LoginUseCase(repository),
            signUp = SignUpUseCase(repository),
            logout = LogoutUseCase(repository)
        )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) =
        UsersUseCases(
            create = CreateUseCase(repository),
            getUserById = GetUserByIdUseCase(repository),
            update = UpdateUseCase(repository),
            saveImage = SaveImageUseCase(repository)
        )

    @Provides
    fun providePostsUseCases(repository: PostRepository) = PostsUseCases(
        createPost = CreatePostUseCase(repository),
        getPosts = GetPostsUseCase(repository),
        getPostsByUserId = GetPostsByUserIdUseCase(repository),
        deletePost = DeletePostUseCase(repository),
        updatePost = UpdatePostUseCase(repository),
        like = LikePostUseCase(repository),
        dislike = DislikePostUseCase(repository)
    )
}