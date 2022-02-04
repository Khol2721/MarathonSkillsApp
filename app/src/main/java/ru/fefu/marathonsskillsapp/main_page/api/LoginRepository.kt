package ru.fefu.marathonsskillsapp.main_page.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.fefu.marathonsskillsapp.main_page.App
import kotlinx.coroutines.flow.Flow


class LoginRepository {

    private val activityApi = App.INSTANCE.retrofit.create(ActivityApi::class.java)

    suspend fun register(
        login: String,
        password: String,
        name: String,
        surname: String,
        email: String,
//        password1: String
    ): Flow<Result<Token>> =
        flow<Result<Token>> {
            emit(
                Result.Success(
                    activityApi.register(
                        login,
                        password,
                        name,
                        surname,
                        email
                    )
                )
            )
        }
            .catch { emit(Result.Errors(it)) }
            .flowOn(Dispatchers.IO)

    suspend fun login(
        login:String,
        password:String
    ): Flow<Result<Token>> =
        flow<Result<Token>> {
            emit(
                Result.Success(
                    activityApi.login(login, password)
                )
            )
        }
        .catch { emit(Result.Errors(it)) }
        .flowOn(Dispatchers.IO)

    suspend fun getProfile(): Flow<Result<User>> =
        flow<Result<User>> {
            emit(
                Result.Success(
                    activityApi.getProfile()
                )
            )
        }
            .catch { emit(Result.Errors(it)) }
            .flowOn(Dispatchers.IO)

    suspend fun logout(): Flow<Result<Unit>> =
        flow<Result<Unit>> {
            emit(
                Result.Success(
                    activityApi.logout()
                )
            )
        }
            .catch { emit(Result.Errors(it)) }
            .flowOn(Dispatchers.IO)
}
