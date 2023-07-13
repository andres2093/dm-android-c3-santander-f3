package com.bedu.terceros

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bedu.terceros.databinding.ActivityFacebookBinding
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog


class FacebookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacebookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFacebookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLink.setOnClickListener {
            val content = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com/"))
                .build()

            ShareDialog.show(this, content)
        }

    }
}