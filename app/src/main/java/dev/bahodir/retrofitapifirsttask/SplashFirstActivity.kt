package dev.bahodir.retrofitapifirsttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.findNavController
import dev.bahodir.retrofitapifirsttask.adapter.VPAdapter
import dev.bahodir.retrofitapifirsttask.databinding.ActivitySplashFirstBinding
import dev.bahodir.retrofitapifirsttask.databinding.FragmentSplashBinding
import dev.bahodir.retrofitapifirsttask.shared.Shared

class SplashFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashFirstBinding
    private lateinit var vpAdapter: VPAdapter
    private lateinit var list: MutableList<Int>
    private lateinit var handler: Handler
    private lateinit var shared: Shared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.visibility = View.INVISIBLE

        listLoad()
        vpAdapter = VPAdapter(list)
        binding.vp.adapter = vpAdapter
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 1500)

        binding.indicator.setViewPager2(binding.vp)

        shared = Shared(this)
        binding.start.setOnClickListener {
            shared.setShared(true)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private var runnable: Runnable = object : Runnable {
        override fun run() {

            binding.start.visibility = View.INVISIBLE
            binding.vp.currentItem = binding.vp.currentItem + 1

            if (binding.vp.currentItem == list.size - 1)
                binding.start.visibility = View.VISIBLE

            handler.postDelayed(this, 1500)

        }

    }

    private fun listLoad() {
        list = mutableListOf()
        list.add(R.drawable.vp1)
        list.add(R.drawable.vp2)
        list.add(R.drawable.vp3)
    }
/*
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host).navigateUp()
    }*/
}