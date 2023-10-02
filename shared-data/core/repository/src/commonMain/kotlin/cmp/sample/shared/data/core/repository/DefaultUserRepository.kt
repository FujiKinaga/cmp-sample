package cmp.sample.shared.data.core.repository

import cmp.sample.shared.data.core.domain.domainobject.user.User
import cmp.sample.shared.data.core.domain.repositoryinterface.UserRepository
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultUserRepository : UserRepository, InstanceKeeper.Instance {

  private val userStateFlow = MutableStateFlow<User?>(null)

  override fun setUser(user: User) {
    userStateFlow.value = user
  }

  override fun getUser(): User? {
    return userStateFlow.value
  }

  override fun clearUser() {
    userStateFlow.value = null
  }

  override fun observeUser(): StateFlow<User?> {
    return userStateFlow.asStateFlow()
  }

  override fun onDestroy() {
    clearUser()
  }
}
