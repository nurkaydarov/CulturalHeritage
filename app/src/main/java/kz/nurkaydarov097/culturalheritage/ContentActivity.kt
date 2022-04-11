package kz.nurkaydarov097.culturalheritage

import android.content.Context

import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kz.nurkaydarov097.culturalheritage.databinding.ActivityContentBinding
import java.util.*

class ContentActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var binding: ActivityContentBinding
    private var academicID:Int = 0
   private var langID:String = ""
    private var context:Context = this
    private var loadingFinished:Boolean = true
    private var redirect:Boolean = false
    //private lateinit var context:Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*****Intent****/
        val intent:Intent = intent
        academicID = intent.getIntExtra(ID_ACADEMIC, 0)
        langID = intent.getStringExtra(LANGUAGE_ID).toString()
        Locale.setDefault(Locale(langID))
        //ChangeLanguage(this).changeLanguage(langID)
        //ChangeLanguage(this).changeLanguage("kk")


        if(intent.getStringExtra(LANGUAGE_ID) != null){
           // langID = intent.getStringExtra(LANGUAGE_ID).toString()
            //ChangeLanguage(this).changeLanguage(langID)
            Log.d("ContentActivity", "CONTENT " + Locale.getDefault().language)
        }
        else{
            //langID = intent.getStringExtra(LANGUAGE_ID).toString()
            //ChangeLanguage(this).changeLanguage(langID)
            Log.d("ContentActivity", "CONTENT " + Locale.getDefault().language)
        }


        /**************/

        /*******Check Internet Connection********/
        val reloadBtn = findViewById<Button>(R.id.reloadBtn)
        if(!isConnected()){
            binding.noInternetContainer.visibility = VISIBLE
            binding.webView.visibility = INVISIBLE
            binding.menuButton.visibility = INVISIBLE
        }
        else{
            binding.noInternetContainer.visibility = INVISIBLE
            binding.webView.visibility = VISIBLE
            binding.menuButton.visibility = VISIBLE

        }

        reloadBtn.setOnClickListener {
            if(isConnected()){
                binding.noInternetContainer.visibility = INVISIBLE
                binding.webView.visibility = VISIBLE
                binding.menuButton.visibility = VISIBLE
                binding.webView.reload()
            }
            else{
                Toast.makeText(this, getString(R.string.noInternetConnection), LENGTH_SHORT).show()
            }
        }


        /*****************************************/


        /************Drawer Layout************************/
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)



        /********************************/



        /******WebView******/
        binding.webView.loadUrl("https://dot.tou.edu.kz")
        val webViewSetting = binding.webView.settings
        webViewSetting.javaScriptEnabled = true
        binding.webView.canGoBack()


       /* binding.webView.setOnTouchListener(object: OnSwipeTouchListener(this){
            override fun onSwipeTop(){
                binding.webView.reload()
            }
        })*/

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
        if(isConnected())
        {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.webView.webViewClient = object: WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    webView: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    if(!loadingFinished){
                        redirect = true
                    }

                    loadingFinished = true
                    if (webView != null) {
                        if (request != null) {
                            webView.loadUrl(request.url.toString())
                            //langID = intent.getStringExtra(LANGUAGE_ID).toString()
                           // ChangeLanguage(context).changeLanguage("kk")
                            Log.d("FIRST", "shouldOverrideUrlLoading " + Locale.getDefault().language)
                        }
                    }
                    return true
                }
            }
        }
        else {
            binding.webView.webViewClient = object: WebViewClient(){
                override fun shouldOverrideUrlLoading(webView: WebView?, url: String?): Boolean {
                    if(isConnected()){
                    if(!loadingFinished){
                        redirect = true
                    }

                    loadingFinished = true

                    if (webView != null) {
                        if (url != null) {

                            if(isConnected()){
                                webView.loadUrl(url)
                            }
                            else{
                                binding.noInternetContainer.visibility = VISIBLE
                                binding.webView.visibility = INVISIBLE
                                binding.menuButton.visibility = INVISIBLE
                            }

                        }
                    }
                    }
                    return true
                }
            }
        }


        binding.webView.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadingFinished = false
                binding.loadingBlock.visibility = VISIBLE
                binding.webView.visibility = INVISIBLE
                binding.menuButton.visibility = INVISIBLE

                //langID = intent.getStringExtra(LANGUAGE_ID).toString()
               // ChangeLanguage(context).changeLanguage("kk")
                Log.d("FIRST", "onPageStarted " + Locale.getDefault().language)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(!redirect){
                    loadingFinished = true
                    binding.loadingBlock.visibility = INVISIBLE
                    binding.webView.visibility = VISIBLE
                    binding.menuButton.visibility = VISIBLE

                    //langID = intent.getStringExtra(LANGUAGE_ID).toString()
                    //ChangeLanguage(context).changeLanguage("kk")
                    Log.d("FIRST", "onPageFinished " + Locale.getDefault().language)
                }
                else{
                    redirect = false
                }
            }
        }
        }




        if(isConnected())
        {

        }
        when(academicID){
            0 -> {
                binding.webView.loadUrl("https://bukharzhirau.tou.edu.kz/")
                //langID = intent.getStringExtra(LANGUAGE_ID).toString()
               // ChangeLanguage(this).changeLanguage(langID)
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
                binding.webView.loadUrl("https://bekhozhin.tou.edu.kz/")
            }
            6 -> {
                binding.webView.loadUrl("https://aimauytov.tou.edu.kz/")
            }
            7 -> {
                binding.webView.loadUrl("https://bekmakhanov.tou.edu.kz/")
            }
            8 -> {
                binding.webView.loadUrl("https://bekturov.tou.edu.kz/")
            }
            9 -> {
                binding.webView.loadUrl("https://margulan.tou.edu.kz/")
            }
            10 -> {
                binding.webView.loadUrl("https://shezhyre.tou.edu.kz/")
            }
            else -> {
                binding.webView.loadUrl("https://bukharzhirau.tou.edu.kz/")
            }
        }

        /*******WebView End***********/


        /*
        /**********Bottom sheet menu*******************/

        // получение вью нижнего экрана
        val lBottomSheet: LinearLayout = findViewById(R.id.bottom_sheet) as LinearLayout

        // настройка поведения нижнего экрана
        val bottomSheetBehavior = BottomSheetBehavior.from(lBottomSheet)
        // настройка состояний нижнего экрана
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        // настройка максимальной высоты
        bottomSheetBehavior.setPeekHeight(340);

        bottomSheetBehavior.setHideable(true);
        /*****************Bottom sheet menu END******************************/
        */

        /****************Floating Button*************************/
        binding.menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        /*****************Floating Button END******************************/



    }


    /*override fun attachBaseContext(base: Context) {
        LocaleHelper().setLocale(base, LocaleHelper().getLanguage(base))
        super.attachBaseContext(LocaleHelper().onAttach(base))
    }*/



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
        //outState.putString(LANGUAGE_ID, langID)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(isConnected()) {
            when (item.itemId) {
                R.id.home_item -> {
                    val intent = Intent(this, MainActivity::class.java)
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.putExtra(LANGUAGE_ID, langID)
                    //this.startActivity(intent)
                    finish()
                }
                R.id.bukharzhirau_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.bukharzhirau_link))
                }

                R.id.IsaBayzakov_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.isaBaizakov_link))
                }
                R.id.mashhur_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.mashhur_link))
                }
                R.id.toraighyrov_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.toraigyrov_link))
                }
                R.id.satbayev_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.satbayev_link))
                }
                R.id.bekhozhin_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.bekhozhin_link))
                }
                R.id.aimauytov_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.aimauytov_link))
                }
                R.id.bekmakhanov_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.bekmakhanov_link))
                }
                R.id.bekturov_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.bekturov_link))
                }
                R.id.margulan_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.margulan_link))
                }
                R.id.shezhire_item -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.shezhire_link))
                }
                else -> {
                    binding.webView.clearHistory()
                    binding.webView.loadUrl(getString(R.string.bukharzhirau_link))
                }
            }
        }
        else{
            binding.noInternetContainer.visibility = VISIBLE
            binding.webView.visibility = INVISIBLE
            binding.menuButton.visibility = INVISIBLE
        }
        binding.webView.clearHistory()
        binding.drawerLayout.closeDrawers()
        return false
    }

        fun isConnected(): Boolean {

            var result = false
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
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

    override fun onStop() {

        super.onStop()
    }



    override fun onDestroy() {
        binding.webView.clearCache(true)
        binding.webView.removeAllViews()
        binding.webView.destroy()
        super.onDestroy()
    }

        companion object {
            @JvmStatic
            val ID_ACADEMIC = "ID_ACADEMIC"
            val  LANGUAGE_ID = "LANGUAGE_ID"
        }

}