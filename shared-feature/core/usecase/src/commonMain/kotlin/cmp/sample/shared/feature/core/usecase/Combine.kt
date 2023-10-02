package cmp.sample.shared.feature.core.usecase

import kotlinx.coroutines.flow.Flow

fun <T1, T2, T3, T4, T5, T6, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  transform: (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow,
    flow2 = flow2,
    flow3 = flow3,
    ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4,
    flow2 = flow5,
    flow3 = flow6,
    transform = ::Triple
  )
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6> ->
  transform(a.first, a.second, a.third, b.first, b.second, b.third)
}

fun <T1, T2, T3, T4, T5, T6, T7, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  transform: (T1, T2, T3, T4, T5, T6, T7) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow,
    flow2 = flow2,
    flow3 = flow3,
    ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4,
    flow2 = flow5,
    flow3 = flow6,
    transform = ::Triple
  ),
  flow7,
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: T7 ->
  transform(a.first, a.second, a.third, b.first, b.second, b.third, c)
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow,
    flow2 = flow2,
    flow3 = flow3,
    ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4,
    flow2 = flow5,
    flow3 = flow6,
    transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7,
    flow2 = flow8,
    transform = ::Pair
  )
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Pair<T7, T8> ->
  transform(a.first, a.second, a.third, b.first, b.second, b.third, c.first, c.second)
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  )
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9> ->
  transform(a.first, a.second, a.third, b.first, b.second, b.third, c.first, c.second, c.third)
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  ),
  flow10,
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9>, d: T10 ->
  transform(a.first, a.second, a.third, b.first, b.second, b.third, c.first, c.second, c.third, d)
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow10, flow2 = flow11, transform = ::Pair
  ),
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9>, d: Pair<T10, T11> ->
  transform(
    a.first,
    a.second,
    a.third,
    b.first,
    b.second,
    b.third,
    c.first,
    c.second,
    c.third,
    d.first,
    d.second
  )
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow10, flow2 = flow11, flow3 = flow12, transform = ::Triple
  ),
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9>, d: Triple<T10, T11, T12> ->
  transform(
    a.first,
    a.second,
    a.third,
    b.first,
    b.second,
    b.third,
    c.first,
    c.second,
    c.third,
    d.first,
    d.second,
    d.third
  )
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow10, flow2 = flow11, flow3 = flow12, transform = ::Triple
  ),
  flow13
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9>, d: Triple<T10, T11, T12>, e: T13 ->
  transform(
    a.first,
    a.second,
    a.third,
    b.first,
    b.second,
    b.third,
    c.first,
    c.second,
    c.third,
    d.first,
    d.second,
    d.third,
    e
  )
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow10, flow2 = flow11, flow3 = flow12, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow13, flow2 = flow14, transform = ::Pair
  ),
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9>, d: Triple<T10, T11, T12>, e: Pair<T13, T14> ->
  transform(
    a.first,
    a.second,
    a.third,
    b.first,
    b.second,
    b.third,
    c.first,
    c.second,
    c.third,
    d.first,
    d.second,
    d.third,
    e.first,
    e.second,
  )
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> combine(
  flow: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  transform: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(
  kotlinx.coroutines.flow.combine(
    flow = flow, flow2 = flow2, flow3 = flow3, ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow4, flow2 = flow5, flow3 = flow6, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow7, flow2 = flow8, flow3 = flow9, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow10, flow2 = flow11, flow3 = flow12, transform = ::Triple
  ),
  kotlinx.coroutines.flow.combine(
    flow = flow13, flow2 = flow14, flow3 = flow15, transform = ::Triple
  ),
) { a: Triple<T1, T2, T3>, b: Triple<T4, T5, T6>, c: Triple<T7, T8, T9>, d: Triple<T10, T11, T12>, e: Triple<T13, T14, T15> ->
  transform(
    a.first,
    a.second,
    a.third,
    b.first,
    b.second,
    b.third,
    c.first,
    c.second,
    c.third,
    d.first,
    d.second,
    d.third,
    e.first,
    e.second,
    e.third
  )
}
