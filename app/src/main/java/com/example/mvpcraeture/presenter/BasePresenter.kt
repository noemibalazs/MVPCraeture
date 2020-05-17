package com.example.mvpcraeture.presenter

import java.lang.ref.WeakReference

abstract class BasePresenter<V> {

    private var view: WeakReference<V>? = null

    fun setView(view: V) {
        this.view = WeakReference(view)
    }

    fun getView(): V? {
        return view?.get()
    }
}