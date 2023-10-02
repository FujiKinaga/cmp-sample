package cmp.sample.shared.data.core.domain.domainobject.user

import cmp.sample.shared.data.core.domain.domainobject.id.UserId

data class User(
  val id: UserId,
  val name: String,
)
