package com.harsh.clevertapapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.clevertap.android.sdk.CleverTapAPI
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val imgUrl =
        "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtProductTitle.text = "CleverTap"
        Glide.with(this).load(imgUrl).transition(withCrossFade()).into(imgProduct)

        val cleverTapAPI = CleverTapAPI.getDefaultInstance(applicationContext)

        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Harsh Mittal"
        profileUpdate["Email"] = "dk+mittalharsh2810@gmail.com"

        btnSubmit.setOnClickListener {

            cleverTapAPI?.pushProfile(profileUpdate)

            val prodViewedAction =
                HashMap<String, Any>()
            prodViewedAction["Product Name"] = txtProductTitle.text.toString()
            prodViewedAction["Product Image"] = imgUrl
            prodViewedAction["Product ID"] = 1
            cleverTapAPI?.pushEvent("Product viewed", prodViewedAction)

        }

    }
}
