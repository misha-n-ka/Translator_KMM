package com.mkirilkinprojects.translator_kmm.core.domain.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual open class CommonMutableStateFlow<T> actual constructor(
    private val mutableStateFlow: MutableStateFlow<T>
) : CommonStateFlow<T>(mutableStateFlow), MutableStateFlow<T> {

    /* TODO Разница в реализации CommonFlow/CommonStateFlow/CommonMutableStateFlow
     * common android/ios пакеты должны содержать платформозависимую логику, но
     * здесь нет никакой платформозависимой логики. Можно просто использовать делегирование
     * by mutableStateFlow, т.к. переопределенные ниже функции тупо делегируют свое выполнение
     *
     * Проверить!
     */

    override var value: T
        get() = super.value
        set(value) {
            mutableStateFlow.value = value
        }

    override val subscriptionCount: StateFlow<Int>
        get() = mutableStateFlow.subscriptionCount

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        mutableStateFlow.resetReplayCache()
    }

    override fun tryEmit(value: T): Boolean {
        return mutableStateFlow.tryEmit(value)
    }

    override fun compareAndSet(expect: T, update: T): Boolean {
        return mutableStateFlow.compareAndSet(expect, update)
    }

    override suspend fun emit(value: T) {
        mutableStateFlow.emit(value)
    }
}