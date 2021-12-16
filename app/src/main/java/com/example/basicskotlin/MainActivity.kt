package com.example.basicskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater) //inflar o formar layout a partir de main binding y guardar todo en binding
        setContentView(binding.root)
        setListeners()

    }

    private fun setListeners(){
        binding.btnSave.setOnClickListener {
           val heroName= binding.etHeroAlter.text.toString()
            val alter=binding.etHeroAlter.text.toString()
            val bio=binding.etBio.text.toString()
            val power=binding.rbPower.rating
            val hero=Hero(heroName,alter,bio,power)
            openDetailActivity(hero)
        }
    }

    private fun openDetailActivity(hero:Hero){
        // Intent es como enviar un sobre con Origen,Destinatario
        //Explicit intent es cuando sabemos que intent vamos a abrir
        val intent= Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY,hero)
        startActivity(intent)
    }
}