package cmp.sample.shared.data.core.service

import cmp.sample.shared.data.core.serviceinterface.ValidatePasswordService

class DefaultValidatePasswordService : ValidatePasswordService {

  override suspend fun validate(password: String): Result<String> {
    return if (REGEX.matches(password)) {
      Result.success(password)
    } else {
      Result.failure(IllegalArgumentException("Invalid password"))
    }
  }

  companion object {

    private val REGEX = "^[a-zA-Z0-9]{8,16}$".toRegex()
  }
}
