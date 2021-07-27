package com.project.projectgithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProfileLengkap : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_lengkap)

        val profil = intent.getParcelableExtra<Profile>(MainActivity.EXTRA_PARCELABLE)
        val imgsrc = findViewById<ImageView>(R.id.img_item_photo_profil)
        val namasrc = findViewById<TextView>(R.id.lengkap_nama)
        val detailsrc = findViewById<TextView>(R.id.lengkap_detail)
        val worksrc = findViewById<TextView>(R.id.lengkap_work)
        val followingsrc = findViewById<TextView>(R.id.text_following)
        val followerssrc = findViewById<TextView>(R.id.text_followers)
        val kerja = findViewById<TextView>(R.id.tempat_kerja)
        val lokasi = findViewById<TextView>(R.id.alamat)

        imgsrc.setImageResource(profil!!.photo)
        namasrc.text = profil.name
        detailsrc.text = profil.detail
        worksrc.text = profil.pekerjaan
        followingsrc.text = profil.following
        followerssrc.text = profil.followers
        kerja.text= profil.tempatkerja
        lokasi.text= profil.lokasi
    }
}