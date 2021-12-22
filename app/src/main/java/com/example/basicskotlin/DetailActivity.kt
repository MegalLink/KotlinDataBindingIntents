package com.example.basicskotlin

import android.graphics.Bitmap
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
        val bitmap=bundle.getParcelable<Bitmap>(IMAGE_KEY)!!
        binding.hero=hero
        binding.ivHero.setImageBitmap(bitmap)
    }
}