package kz.nurkaydarov097.culturalheritage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import kz.nurkaydarov097.culturalheritage.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    private var academicID:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        /*****Intent****/
        val intent:Intent = intent
        academicID = intent.getIntExtra(ID_ACADEMIC, 0)
        /**************/

        /******WebView******/
        binding.webView.loadUrl("https://dot.tou.edu.kz")
        val webViewSetting = binding.webView.settings
        webViewSetting.javaScriptEnabled = true
        binding.webView.canGoBack()
        binding.webView.setOnKeyListener(View.OnKeyListener{ v, keyCode, event ->

            if( keyCode == KeyEvent.KEYCODE_BACK
                && event.action == MotionEvent.ACTION_UP
                && binding.webView.canGoBack()
            ){
                binding.webView.goBack()
                return@OnKeyListener true
            }

           false

        })
        /******************/


        when(academicID){
            0 -> {
                binding.webView.loadUrl("https://bukharzhirau.tou.edu.kz/")
            }
            1 -> {
                binding.webView.loadUrl("https://isa-baizakov.tou.edu.kz/")
            }
            2 -> {
                binding.webView.loadUrl("https://mashhur.tou.edu.kz/")
            }
            3 -> {
                binding.webView.loadUrl("https://toraigyrov.tou.edu.kz/")
            }
            4 -> {
                binding.webView.loadUrl("https://satpaev.tou.edu.kz/")
            }
            5 -> {
                binding.webView.loadUrl("https://satpaev.tou.edu.kz/")
            }
            6 -> {
                binding.webView.loadUrl("https://bekhozhin.tou.edu.kz/")
            }
            7 -> {
                binding.webView.loadUrl("https://aimauytov.tou.edu.kz/")
            }
            8 -> {
                binding.webView.loadUrl("https://bekmakhanov.tou.edu.kz/")
            }
            9 -> {
                binding.webView.loadUrl("https://bekturov.tou.edu.kz/")
            }
            10 -> {
                binding.webView.loadUrl("https://shezhyre.tou.edu.kz/")
            }
            else -> {
                binding.webView.loadUrl("https://margulan.tou.edu.kz/")
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ID_ACADEMIC, academicID)
    }

    companion object{
        @JvmStatic
        val ID_ACADEMIC = "ID_ACADEMIC"
    }
}