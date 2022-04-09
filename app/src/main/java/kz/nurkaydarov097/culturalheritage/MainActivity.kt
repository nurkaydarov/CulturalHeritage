package kz.nurkaydarov097.culturalheritage

import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import kz.nurkaydarov097.culturalheritage.adapters.AcademicAdapter
import kz.nurkaydarov097.culturalheritage.databinding.ActivityMainBinding
import kz.nurkaydarov097.culturalheritage.models.Datasource
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding
    private var langID:String = Locale.getDefault().language
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        //langID = "kk"
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initRecyclerView("")

        Log.d("LANG", "Locale " +Locale.getDefault().language)

        binding.language.setOnClickListener { view->
            showMenuLanguages(view)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LANGUAGE_ID, langID)
        super.onSaveInstanceState(outState)

    }

    fun initRecyclerView(language:String){
        val dataset = Datasource().loadAcademics()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = AcademicAdapter(this, dataset, language)
    }


    fun showMenuLanguages(view: View){
        val popup = PopupMenu(this,view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_language, popup.menu)
            popup.setOnMenuItemClickListener { language ->
                    when(language.itemId){
                         R.id.select_language_kz -> {

                             updateLocale(Locale("kk"))

                         }
                         R.id.select_language_ru -> {
                             updateLocale(Locale("ru"))
                            // LocaleHelper().setLocale(this@MainActivity, "ru")
                            // val intent = Intent(this, MainActivity::class.java)
                            // intent.putExtra(LANGUAGE_ID, "ru")
                             //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                             //startActivity(intent)
                            // finish()
                         }
                         R.id.select_language_en -> {
                             updateLocale(Locale.ENGLISH)
                            // val intent = Intent(this, MainActivity::class.java)
                           //  intent.putExtra(LANGUAGE_ID, "en")
                             //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                           //  startActivity(intent)
                           //  finish()

                         }
                         else -> {}

                    }
                false
            }
        popup.show()
    }

    companion object{
        @JvmStatic val LANGUAGE_ID = "LANGUAGE_ID"
    }

    }
