package com.project.projectgithubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var rvProfile: RecyclerView
    private var list: ArrayList<Profile> = arrayListOf()

    companion object {
        val EXTRA_PARCELABLE = "extra_parcelable"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProfile = findViewById(R.id.profileUser)
        rvProfile.setHasFixedSize(true)

        list.addAll(ProfileData.listData)
        showRecyclerList()

        val beritasatu: ImageView = findViewById(R.id.beritasatu)
        beritasatu.setOnClickListener(this)

        val beritadua: ImageView = findViewById(R.id.beritadua)
        beritadua.setOnClickListener(this)

        val beritatiga: ImageView = findViewById(R.id.beritatiga)
        beritatiga.setOnClickListener(this)
    }

    private fun showRecyclerList() {
        rvProfile.layoutManager = LinearLayoutManager(this)
        val myAdapter =MyAdapter(list)
        rvProfile.adapter = myAdapter

        myAdapter.setOnItemClickCallBack(object : MyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Profile) {
                val moveProfile = Intent(this@MainActivity, ProfileLengkap::class.java)
                moveProfile.putExtra(EXTRA_PARCELABLE, data)
                startActivity(moveProfile)
    }

        })
    }
    override fun onClick (v: View){
        when (v.id){
            R.id.beritasatu ->{
                Toast.makeText(this, "Opening", Toast.LENGTH_SHORT).show()
            }

            R.id.beritadua -> {
                Toast.makeText(this, "Opening", Toast.LENGTH_SHORT).show()
            }
            R.id.beritatiga -> {
                Toast.makeText(this, "Opening", Toast.LENGTH_SHORT).show()
            }
        }
    }
}