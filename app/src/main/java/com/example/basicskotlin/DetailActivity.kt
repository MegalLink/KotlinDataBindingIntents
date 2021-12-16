package com.example.basicskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicskotlin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object{
        const val HERO_KEY="hero"
    }
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val hero=bundle.getParcelable<Hero>(HERO_KEY)!!

        binding.tvHeroName.text=hero.name
        binding.tvAlter.text=hero.alter
        binding.tvBio.text=hero.bio
        binding.rbPower.rating=hero.power
    }
}