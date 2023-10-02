package cmp.sample.shared.data.core.serviceinterface

interface ValidatePasswordService {

  suspend fun validate(password: String): Result<String>

}
