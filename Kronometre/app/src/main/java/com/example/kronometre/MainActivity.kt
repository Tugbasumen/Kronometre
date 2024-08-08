package com.example.kronometre

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kronometre.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var zamaniDurdur: Long=0
        binding.btnStart.setOnClickListener {
            binding.kronometre.base= SystemClock.elapsedRealtime()+zamaniDurdur
            binding.kronometre.start()
            binding.btnStart.visibility= View.GONE
            binding.btnPause.visibility= View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))
        }

        binding.btnPause.setOnClickListener {
           zamaniDurdur= binding.kronometre.base-SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            binding.btnStart.visibility= View.VISIBLE
            binding.btnPause.visibility= View.GONE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }

        binding.btnReset.setOnClickListener {
            binding.kronometre.base=SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            zamaniDurdur= 0
            binding.btnStart.visibility= View.VISIBLE
            binding.btnPause.visibility= View.GONE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }
    }
}