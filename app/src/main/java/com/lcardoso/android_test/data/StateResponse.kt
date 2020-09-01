package com.lcardoso.android_test.data

sealed class StateResponse<T>

class StateSuccess<T>(val data: T) : StateResponse<T>()
class StateError<T>(val e: Throwable) : StateResponse<T>()
class StateLoading<T> : StateResponse<T>()