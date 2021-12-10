package dev.bahodir.retrofitapifirsttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import dev.bahodir.retrofitapifirsttask.shared.Shared

class ActivitySplash : AppCompatActivity() {
    private lateinit var shared: Shared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        shared = Shared(this)

        object : CountDownTimer(1500, 100) {
            override fun onTick(p0: Long) {

            }
            override fun onFinish() {

                if (shared.getShared() == true) {
                    val intent = Intent(this@ActivitySplash, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    val intent = Intent(this@ActivitySplash, SplashFirstActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }
}