package com.bedu.librerias

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bedu.librerias.databinding.ActivityMainBinding
import shortbread.Shortcut

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnToasty.setOnClickListener {
            val intent = Intent(this, ToastyActivity::class.java)
            startActivity(intent)
        }
        binding.btnChart.setOnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            startActivity(intent)
        }
        binding.btnShortbread.setOnClickListener {
            val intent = Intent(this, ShortbreadActivity::class.java)
            startActivity(intent)
        }
        binding.btnFresco.setOnClickListener {
            val intent = Intent(this, FrescoActivity::class.java)
            startActivity(intent)
        }
        binding.btnFragment.setOnClickListener {
            addFragment()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
                if (fragment != null) {
                    supportFragmentManager.beginTransaction().remove(fragment).commitNow()
                    visibility(View.VISIBLE)
                } else {
                    finish()
                }
            }
        })
    }

    @Shortcut(id = "ScrollingFragment", shortLabel = "Mostrar texto")
    fun addFragment(){
        visibility(View.GONE)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ScrollingFragment(), "ScrollingFragment")
            .commitNow()
    }

    private fun visibility(visibility: Int){
        binding.txvTitle.visibility = visibility
        binding.btnToasty.visibility = visibility
        binding.btnChart.visibility = visibility
        binding.btnShortbread.visibility = visibility
        binding.btnFresco.visibility = visibility
        binding.btnFragment.visibility = visibility
    }
}