package cmp.sample.shared.screen.login.usecaseinterface

interface LoginUseCase {

  suspend fun checkEmail(email: String): Result<String>

  suspend fun checkPassword(password: String): Result<String>

  suspend fun login(email: String, password: String): Result<Unit>
}
