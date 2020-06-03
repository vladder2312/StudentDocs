package com.vladder2312.studentdocs.ui.document

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.domain.Photo
import com.vladder2312.studentdocs.ui.photo.PhotoActivity
import com.vladder2312.studentdocs.ui.photo.PhotoListController
import kotlinx.android.synthetic.main.activity_document.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class DocumentActivity : MvpAppCompatActivity(), DocumentView {

    @InjectPresenter
    lateinit var presenter: DocumentPresenter
    private val photoAdapter = EasyAdapter()
    private val photoListController = PhotoListController {
        startPhotoActivity(it.uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)

        initRecycler()
        initSpinner()
        initViews()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.document_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_button -> {

            }
            R.id.delete_button -> {

            }
            R.id.done_button -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initListeners() {
        document_name.addTextChangedListener {
            presenter.nameChanged(it.toString())
        }
        document_category.setOnItemClickListener { _, _, position, _ ->
            presenter.categorySelected(position)
        }
    }

    override fun initRecycler() {
        photoAdapter.setItems(ItemList())
        document_photos.layoutManager =
            GridLayoutManager(document_photos.context, 1, GridLayoutManager.HORIZONTAL, false)
        document_photos.adapter = photoAdapter
    }

    override fun initSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.categories)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        document_category.adapter = adapter
        document_category.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    presenter.categorySelected(position)
                }
            }
    }

    override fun initViews() {
        val document: Document = intent.getSerializableExtra("document") as Document
        title = document.name
        document_name.setText(document.name)
        when (document.category) {
            Category.Document -> {
                document_category.setSelection(0)
            }
            Category.Certificate -> {
                document_category.setSelection(1)
            }
            Category.Reward -> {
                document_category.setSelection(2)
            }
        }

        presenter.loadPhotos(document.id)
    }

    override fun setPhotos(photos: List<Photo>) {
        photoAdapter.setData(photos, photoListController)
    }

    override fun startPhotoActivity(uri: String) {
        val intent = Intent(applicationContext, PhotoActivity::class.java)
        intent.putExtra("uri", uri)
        startActivity(intent)
    }

    override fun changeMode(on: Boolean) {

    }
}