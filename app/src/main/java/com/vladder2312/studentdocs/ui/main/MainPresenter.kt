package com.vladder2312.studentdocs.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Презентер главной активити
 */
@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    val model = MainModel()
}