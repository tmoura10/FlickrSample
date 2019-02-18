package br.com.tmoura.flickrsample.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface RxSchedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}

class RxSchedulersImpl @Inject constructor() : RxSchedulers {
    override fun background() = Schedulers.io()
    override fun main() = AndroidSchedulers.mainThread()
}
