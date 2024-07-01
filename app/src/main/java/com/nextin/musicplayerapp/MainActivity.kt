package com.nextin.musicplayerapp

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nextin.musicplayerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     val binding : ActivityMainBinding by lazy{
         ActivityMainBinding.inflate(layoutInflater)
     }
    lateinit var  mediaPalyer : MediaPlayer
    var totaltime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mediaPalyer = MediaPlayer.create(this,R.raw.music)
        mediaPalyer.setVolume(1f,1f)
        totaltime = mediaPalyer.duration


        binding.btnStart.setOnClickListener {
            mediaPalyer.start()
        }
        binding.btnPause.setOnClickListener {
            mediaPalyer.pause()
        }
        binding.btnStop.setOnClickListener {
            mediaPalyer.stop()
            mediaPalyer.reset()
            mediaPalyer.release()
        }

        val seekbar = binding.musicSeekBar

        seekbar.max = totaltime
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               if (fromUser){
                   mediaPalyer.seekTo(progress)
               }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                seekbar.progress = mediaPalyer.currentPosition
                handler.postDelayed(this,1000)
            }
        },0)
    }
}