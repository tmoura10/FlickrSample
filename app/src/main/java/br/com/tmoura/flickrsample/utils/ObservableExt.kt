package br.com.tmoura.flickrsample.utils

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.throttle(): Observable<T> = this.share().throttleFirst(1, TimeUnit.SECONDS)
