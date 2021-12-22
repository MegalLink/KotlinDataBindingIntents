package com.example.basicskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.basicskotlin.databinding.ActivityMainBinding
import android.os.Environment
import java.io.File
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.content.FileProvider


class MainActivity : AppCompatActivity() {

    //late init le prometemos a kotlin que cuando se usea la variable ya va a tener un valor, osea que debemos asignarle un valor si o si en onCreate
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroImage:ImageView
    private var picturePath = ""
    //Ejecutar camara
    private val getCameraBitmap= registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
       if(success&&picturePath.isNotEmpty()){
           val heroBitmap=BitmapFactory.decodeFile(picturePath)
           heroImage.setImageBitmap(heroBitmap)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater) //inflar o formar layout a partir de main binding y guardar todo en binding
        setContentView(binding.root)

        //Set on create
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
        val file=createImageFile()
        val uri= if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
           //VERSION NUEVA necesita permisos para guardar la foto en android manifest se envia lo de meta-data $packageName.provider
            FileProvider.getUriForFile(this,"com.example.basicskotlin.provider",file)
        }else{
            //VERSION VIEJA
            Uri.fromFile(file)
        }
        getCameraBitmap.launch(uri)
    }

    private fun createImageFile(): File {
        val fileName = "hero_image"
        //getExternalFilesDir use provider_paths.xml
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file=File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath=file.absolutePath
        return file
    }

    private fun openDetailActivity(hero:Hero){
        // Intent es como enviar un sobre con Origen,Destinatario
        //Explicit intent es cuando sabemos que intent vamos a abrir
        val intent= Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY,hero)
        Log.d("openDetailActivity",picturePath)
        intent.putExtra(DetailActivity.IMAGE_KEY,picturePath)
        startActivity(intent)
    }
}