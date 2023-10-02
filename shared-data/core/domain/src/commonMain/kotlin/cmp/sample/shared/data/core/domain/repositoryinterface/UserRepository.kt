package cmp.sample.shared.data.core.domain.repositoryinterface

import kotlinx.coroutines.flow.StateFlow
import cmp.sample.shared.data.core.domain.domainobject.user.User

interface UserRepository {

  fun setUser(user: User)

  fun getUser(): User?

  fun clearUser()

  fun observeUser(): StateFlow<User?>
}
