package cmp.sample.shared.data.core.serviceinterface

interface ValidateEmailService {

  suspend fun validate(email: String): Result<String>
}
