package com.darta.minder.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.darta.minder.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val image = intent.getParcelableExtra<Image>(NotificationsFragment.INTENT_PARCELABLE)

        val imgSrc = findViewById<ImageView>(R.id._imageDetail)

        imgSrc.setImageResource(image!!.imageSrc)
    }
}