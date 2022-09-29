package uz.unidev.servicestest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.unidev.servicestest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }
    }
}