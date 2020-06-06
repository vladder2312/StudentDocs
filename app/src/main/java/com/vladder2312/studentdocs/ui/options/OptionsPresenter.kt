package com.vladder2312.studentdocs.ui.options

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class OptionsPresenter : MvpPresenter<OptionsView>() {

    val model = OptionsModel()
}