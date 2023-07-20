package com.bedu.librerias

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutManagerCompat.ShortcutMatchFlags
import com.bedu.librerias.databinding.ActivityShortbreadBinding
import shortbread.Shortcut

@Shortcut(id = "shortBread", icon = R.drawable.ic_android, shortLabel = "ShortBread")
class ShortbreadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortbreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShortbreadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}