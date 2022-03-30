package kz.nurkaydarov097.culturalheritage

import android.app.Service
import android.content.Context

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import kz.nurkaydarov097.culturalheritage.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    private var academicID:Int = 0
    //private lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        /*******Check Internet Connection********/
        val reloadBtn = findViewById<Button>(R.id.reloadBtn)
        if(!isConnected()){
            binding.noInternetContainer.visibility = VISIBLE
            binding.webView.visibility = INVISIBLE
        }
        else{

            if(isConnected()){
                reloadBtn.setOnClickListener {
                    binding.noInternetContainer.visibility = INVISIBLE
                    binding.webView.visibility = VISIBLE
                    binding.webView.reload()


                }
            }


        }


        /*****************************************/


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


    fun isConnected():Boolean{

        var result = false
       val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)

            if (actNw != null) {
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        else {
            connectivityManager.run{
                connectivityManager.activeNetworkInfo?.run{
                    result = when(type){
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    companion object{
        @JvmStatic
        val ID_ACADEMIC = "ID_ACADEMIC"
    }
}