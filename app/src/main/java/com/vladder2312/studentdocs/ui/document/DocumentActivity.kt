package com.vladder2312.studentdocs.ui.document

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.domain.Photo
import com.vladder2312.studentdocs.ui.photo.PhotoActivity
import com.vladder2312.studentdocs.ui.photo.PhotoDialog
import com.vladder2312.studentdocs.ui.photo.PhotoListController
import com.vladder2312.studentdocs.utils.PermissionChecker
import com.vladder2312.studentdocs.utils.UriCreator
import kotlinx.android.synthetic.main.activity_document.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

/**
 * Активити с информацией о документе
 */
class DocumentActivity : MvpAppCompatActivity(), DocumentView {

    @InjectPresenter
    lateinit var presenter: DocumentPresenter
    private lateinit var doneButton: MenuItem
    private val photoAdapter = EasyAdapter()
    private val photoListController = PhotoListController(
        {
            startPhotoActivity(it.uri)
        },
        {
            showPhotoDeleteDialog(it)
            true
        }
    )
    private lateinit var cameraUri: Uri
    private val REQUEST_CAMERA = 22
    private val REQUEST_GALLERY = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)

        initRecycler()
        initSpinner()
        initViews()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.document_menu, menu)
        doneButton = menu.getItem(2)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_button -> {
                changeMode(true)
            }
            R.id.delete_button -> {
                showDocumentDeleteDialog()
            }
            R.id.done_button -> {
                changeMode(false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initListeners() {
        document_name.addTextChangedListener {
            presenter.nameChanged(it.toString())
        }
        document_add_photo.setOnClickListener {
            PhotoDialog.showDialog(this, this::openCamera, this::openGallery)
        }
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
        presenter.saveToModel(document)
        presenter.loadPhotos()
        document_category.isEnabled = false
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
        val mlp = document_add_photo.layoutParams as ViewGroup.LayoutParams
        doneButton.isVisible = on
        document_name.isEnabled = on
        document_category.isEnabled = on
        document_add_photo.isVisible = on
        if (!on) {
            presenter.updateDocument()
            mlp.width = 0
        } else {
            mlp.width = 160
        }
        document_add_photo.layoutParams = mlp
    }

    fun showDocumentDeleteDialog() {
        AlertDialog.Builder(this)
            .setMessage("Вы уверены, что хотите удалить этот документ?")
            .setPositiveButton("Да") { _: DialogInterface, _: Int ->
                presenter.deleteDocument()
                finish()
            }
            .setNegativeButton("Нет") { _: DialogInterface, _: Int -> }
            .show()
    }

    override fun showPhotoDeleteDialog(photo: Photo) {
        AlertDialog.Builder(this)
            .setMessage("Вы уверены, что хотите удалить фото?")
            .setPositiveButton("Да") { _: DialogInterface, _: Int ->
                presenter.deletePhoto(photo)
            }
            .setNegativeButton("Нет") { _: DialogInterface, _: Int -> }
            .show()
    }

    override fun openCamera() {
        if (PermissionChecker.checkCameraPermission(this)) {
            cameraUri = UriCreator.createUri(applicationContext)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri)
            startActivityForResult(intent, REQUEST_CAMERA)
        }
    }

    override fun openGallery() {
        if (PermissionChecker.checkGalleryPermission(this)) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CAMERA -> {
                    data?.data.let {
                        presenter.addPhoto(cameraUri.toString())
                    }
                }
                REQUEST_GALLERY -> {
                    data?.data.let {
                        presenter.addPhoto(it.toString())
                    }
                }
            }
        }
    }
}