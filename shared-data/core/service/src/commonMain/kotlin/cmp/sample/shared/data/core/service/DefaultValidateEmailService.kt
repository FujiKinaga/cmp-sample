package cmp.sample.shared.data.core.service

import cmp.sample.shared.data.core.serviceinterface.ValidateEmailService

class DefaultValidateEmailService : ValidateEmailService {

  override suspend fun validate(email: String): Result<String> {
    return if (REGEX.matches(email)) {
      Result.success(email)
    } else {
      Result.failure(IllegalArgumentException("Invalid email"))
    }
  }

  companion object {

    private val REGEX = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
  }
}
