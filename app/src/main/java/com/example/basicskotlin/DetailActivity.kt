package com.example.basicskotlin

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicskotlin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object{
        const val HERO_KEY="hero"
        const val IMAGE_KEY="bitmap"
    }
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val hero=bundle.getParcelable<Hero>(HERO_KEY)!!
        val bitmapDirectory=bundle.getString(IMAGE_KEY)
        val newBitmap= if(!bitmapDirectory.isNullOrEmpty()){
            BitmapFactory.decodeFile(bitmapDirectory)
        }else{
            BitmapFactory.decodeResource(resources, R.drawable.ic_menu_camera)
        }

        binding.hero=hero
        binding.ivHero.setImageBitmap(newBitmap)
    }
}