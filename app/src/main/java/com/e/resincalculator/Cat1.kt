package com.e.resincalculator

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cat1.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.security.PrivateKey


class Cat1 : AppCompatActivity() {
    var onvan:String = ""
    var matn:String = ""
    var link:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat1)

        imgNavigationTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_gray))
        imgNavigationTop.setOnClickListener {
            finish()
        }

        onvan = intent.getStringExtra("onvan")
        matn = intent.getStringExtra("matn")
        link = intent.getStringExtra("link")

        txOnvan.text = onvan
        txMatn.text = matn


        val uri: Uri = Uri.parse("https://hw16.cdn.asset.aparat.com/aparat-video/d98025aa25385cf0f4a8ab4d1020ce0814378419-240p__25088.mp4")
        videoView2.setVideoURI(uri);
        videoView2.start();
        imgPlayVideo2.visibility = View.GONE
        videoView2.setOnClickListener{
            //PrivateKey.serialVersionUID.and("" + "")
            if (videoView2.isPlaying){
                videoView2.stopPlayback()
                imgPlayVideo2.visibility = View.VISIBLE
            }else{
                videoView2.start()
                videoView2.resume();
                imgPlayVideo2.visibility = View.GONE
            }
        }


}}

