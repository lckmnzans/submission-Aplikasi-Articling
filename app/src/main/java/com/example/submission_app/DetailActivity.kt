package com.example.submission_app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    //mendefinisikan object EXTRA_ITEM yg nantinya akan diisi dengan data class
    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //mengambil data class dari activity lain dengan intent
        val item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ITEM, Smartphone::class.java)
        } else {
            @Suppress("DEPRECATED")
            intent.getParcelableExtra(EXTRA_ITEM)
        }

        //mengubah judul activitiy
        supportActionBar!!.title = item?.name

        //mendefinisikan View berdasar id di dalam activity
        val tvItemName: TextView = findViewById(R.id.tv_item_name)
        val tvItemPrice: TextView = findViewById(R.id.tv_item_price)
        val tvItemDesc: TextView = findViewById(R.id.tv_item_description)
        val tvItemSpec: TextView = findViewById(R.id.tv_item_specification)
        val imgItemPhoto: ImageView = findViewById(R.id.img_item_photo)
        //memasukkan value dari object ke dalam View
        if (item != null) {
            tvItemName.text = item.name
            tvItemPrice.text = item.price
            tvItemDesc.text = item.desc
            tvItemSpec.text = item.specification
            imgItemPhoto.setImageResource(item.image)
        }
        //mendefinisikan View button
        val shareButton: Button = findViewById(R.id.action_share)

        //extension function utk View button agar listen terhadap input dari user
        shareButton.setOnClickListener {
            val intentShare = Intent()
            intentShare.action = Intent.ACTION_SEND
            intentShare.putExtra(Intent.EXTRA_TEXT, "${item?.link}")
            intentShare.type = "text/plain"
            startActivity(Intent.createChooser(intentShare, "Bagikan link"))
        }
    }
}