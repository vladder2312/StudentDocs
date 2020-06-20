package com.vladder2312.studentdocs.ui.options

import com.arellomobile.mvp.MvpView

/**
 * Интерфейс View настроек приложения
 */
interface OptionsView : MvpView {

    fun initListeners()
    fun showUsage(memory: String, amount: Int)
    fun showDeleteDialog()
    fun startAboutAppActivity()
}