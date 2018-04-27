package com.jonnyhsia.model.base.rx

import io.reactivex.disposables.CompositeDisposable

interface RxNoLeak {
    val disposable: CompositeDisposable
}