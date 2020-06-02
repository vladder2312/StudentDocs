package com.vladder2312.studentdocs.ui.add_document

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Photo
import com.vladder2312.studentdocs.utils.PermissionChecker
import com.vladder2312.studentdocs.utils.UriCreator
import kotlinx.android.synthetic.main.fragment_add_document.*
import ru.surfstudio.android.easyadapter.EasyAdapter

class AddDocumentFragment : MvpAppCompatFragment(), AddDocumentView {

    @InjectPresenter
    lateinit var presenter: AddDocumentPresenter
    private val adapter = EasyAdapter()
    private val photoListController = PhotoListController {

    }

    private val REQUEST_CAMERA = 22
    private val REQUEST_GALLERY = 21
    private lateinit var cameraUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_add_document, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecycler()
        initListeners()
        initViews()
    }

    override fun initRecycler() {
        foto_recycler.layoutManager =
            GridLayoutManager(foto_recycler.context, 1, GridLayoutManager.HORIZONTAL, false)
        foto_recycler.adapter = adapter
        foto_recycler.isNestedScrollingEnabled = false
    }

    override fun initListeners() {
        add_doc_add_photo.setOnClickListener { _ ->
            context?.let { it ->
                PhotoDialog.showDialog(it, this::openCamera, this::openGallery)
            }
        }
    }

    override fun initViews() {
        val categories = resources.getStringArray(R.array.categories)
        val adapter: ArrayAdapter<String>
        if (context != null) {
            adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, categories)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            add_doc_category.adapter = adapter
            add_doc_category.onItemSelectedListener =
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
    }

    override fun openCamera() {
        if(PermissionChecker.checkCameraPermission(activity!!)){
            cameraUri = UriCreator.createUri(context!!)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri)
            startActivityForResult(intent, REQUEST_CAMERA)
        }
    }

    override fun openGallery() {
        if(PermissionChecker.checkGalleryPermission(activity!!)){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when (requestCode) {
                REQUEST_CAMERA -> {
                    data?.data.let {
                        presenter.photoLoaded(cameraUri.toString())
                    }
                }
                REQUEST_GALLERY -> {
                    data?.data.let {
                        presenter.photoLoaded(it.toString())
                    }
                }
            }
        }
    }

    override fun showPhotos(photos: MutableList<Photo>) {
        adapter.setData(photos, photoListController)
    }
}