package com.bedu.hilt.ui.main.view

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bedu.hilt.R
import com.bedu.hilt.databinding.ActivityMainBinding
import com.bedu.hilt.ui.people.view.PeopleActivity
import com.bedu.hilt.ui.planets.view.PlanetsActivity
import com.bedu.hilt.utils.DownloadController
import com.bedu.hilt.utils.checkSelfPermissionCompat
import com.bedu.hilt.utils.requestPermissionsCompat
import com.bedu.hilt.utils.shouldShowRequestPermissionRationaleCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val PERMISSION_REQUEST_STORAGE = 0
        const val urlApp = "https://github.com/andres2093/apk-s/raw/sesion6/app-release.apk"
    }

    private lateinit var downloadController: DownloadController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        downloadController = DownloadController(this, urlApp)

        setupUI()
    }

    private fun setupUI() {
        binding.btnPeople.setOnClickListener {
            val intent = Intent(this, PeopleActivity::class.java)
            startActivity(intent)
        }
        binding.btnPlanets.setOnClickListener {
            val intent = Intent(this, PlanetsActivity::class.java)
            startActivity(intent)
        }
        binding.btnDownload.setOnClickListener {
            checkStoragePermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadController.enqueueDownload()
            } else {
                Toast.makeText(this, R.string.storage_permission_denied, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkStoragePermission() {
        if (checkSelfPermissionCompat(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            downloadController.enqueueDownload()
        } else {
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {
        if (shouldShowRequestPermissionRationaleCompat(WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, R.string.storage_permission_denied, Toast.LENGTH_LONG).show()
            requestPermissionsCompat(
                arrayOf(WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_STORAGE
            )
        } else {
            requestPermissionsCompat(
                arrayOf(WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_STORAGE
            )
        }
    }
}
