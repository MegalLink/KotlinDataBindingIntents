package com.example.basicskotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import com.example.basicskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
       const val CAMERA_INTENT_RC=1000
    }
    //late init le prometemos a kotlin que cuando se usea la variable ya va a tener un valor, osea que debemos asignarle un valor si o si despues
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater) //inflar o formar layout a partir de main binding y guardar todo en binding
        setContentView(binding.root)

        heroImage=binding.ivAvatar
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

        heroImage.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        //Implicit Intent abri camara
        val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_INTENT_RC)
    }

    private fun openDetailActivity(hero:Hero){
        // Intent es como enviar un sobre con Origen,Destinatario
        //Explicit intent es cuando sabemos que intent vamos a abrir
        val intent= Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY,hero)
        intent.putExtra(DetailActivity.IMAGE_KEY,heroImage.drawable.toBitmap())
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK && requestCode== CAMERA_INTENT_RC){
            val extras=data?.extras;
            val heroBitmap=extras?.getParcelable<Bitmap>("data")
            heroImage.setImageBitmap(heroBitmap)
        }
    }
}