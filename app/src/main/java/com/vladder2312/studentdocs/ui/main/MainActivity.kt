package com.vladder2312.studentdocs.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainer
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.ui.add_document.AddDocumentFragment
import com.vladder2312.studentdocs.ui.documents.DocumentsFragment
import com.vladder2312.studentdocs.ui.options.OptionsFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Главная активити приложения, содержащая 3 фрагмента
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var documentsFragment: DocumentsFragment

    @Inject
    lateinit var addDocumentFragment: AddDocumentFragment

    @Inject
    lateinit var optionsFragment: OptionsFragment

    init {
        App.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()

        supportFragmentManager.beginTransaction().add(R.id.fragment_holder, documentsFragment)
            .commit()
    }

    override fun initListeners() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.action_documents -> {
                    transaction.replace(R.id.fragment_holder, documentsFragment)
                    it.isChecked = true
                }
                R.id.action_add_document -> {
                    transaction.replace(R.id.fragment_holder, addDocumentFragment)
                    it.isChecked = true
                }
                R.id.action_options -> {
                    transaction.replace(R.id.fragment_holder, optionsFragment)
                    it.isChecked = true
                }
            }
            transaction.commit()
            false
        }
    }
}