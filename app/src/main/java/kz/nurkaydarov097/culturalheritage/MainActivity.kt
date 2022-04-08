package kz.nurkaydarov097.culturalheritage

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import kz.nurkaydarov097.culturalheritage.adapters.AcademicAdapter
import kz.nurkaydarov097.culturalheritage.databinding.ActivityMainBinding
import kz.nurkaydarov097.culturalheritage.models.Datasource
import kz.nurkaydarov097.culturalheritage.utils.ChangeLanguage
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var langID:String = Locale.getDefault().language
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        langID = Locale.getDefault().language

        if(intent.getStringExtra(LANGUAGE_ID) == null){
            langID = Locale.getDefault().language
            ChangeLanguage(this).changeLanguage(langID!!)
            Log.d("FIRST", langID)
        }
        else{
            langID = intent.getStringExtra(LANGUAGE_ID).toString()
            ChangeLanguage(this).changeLanguage(langID!!)
            Log.d("FIRST", "SECOND")
        }
        initRecyclerView(langID)


        if(savedInstanceState == null){

        }

        Log.d("LANG", "aa " +langID!!)
        //ChangeLanguage(this).changeLanguage(langID!!)

        



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
                            val intent = Intent(this, MainActivity::class.java)
                             intent.putExtra(LANGUAGE_ID, "kz")
                             startActivity(intent)
                             finish()
                         }
                         R.id.select_language_ru -> {
                             val intent = Intent(this, MainActivity::class.java)
                             intent.putExtra(LANGUAGE_ID, "ru")
                             startActivity(intent)
                             finish()
                         }
                         R.id.select_language_en -> {
                             val intent = Intent(this, MainActivity::class.java)
                             intent.putExtra(LANGUAGE_ID, "en")
                             startActivity(intent)
                             finish()
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
