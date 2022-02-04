package ru.fefu.marathonsskillsapp.main_page.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val loginRepository = LoginRepository()

    private val _dataFlow = MutableSharedFlow<Result<Token>>(replay = 0)

    val dataFlow get() = _dataFlow

    fun register(login: String, name: String, surname: String, email: String, password: String) {
        viewModelScope.launch {
            loginRepository.register(login, name, surname, email, password)
                .collect {
                    when(it) {
                        is Result.Success<*> -> _dataFlow.emit(it)
                        is Result.Errors<*> -> _dataFlow.emit(it)
                    }
                }
        }
    }
}