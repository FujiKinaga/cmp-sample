package cmp.sample.shared.data.core.domainmapper

import cmp.sample.shared.data.core.apiclient.feed.FeedResponse
import cmp.sample.shared.data.core.apiclient.users.UserResponse
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed
import cmp.sample.shared.data.core.domain.domainobject.id.FeedId
import cmp.sample.shared.data.core.domain.domainobject.id.UserId
import cmp.sample.shared.data.core.domain.domainobject.user.User

fun UserResponse.mapToDomainObject(): User {
  return User(
    id = UserId(id),
    name = name,
  )
}

fun FeedResponse.mapToDomainObject(): Feed {
  val feedId = FeedId(id)
  return Feed(
    id = feedId,
    title = title,
  )
}
