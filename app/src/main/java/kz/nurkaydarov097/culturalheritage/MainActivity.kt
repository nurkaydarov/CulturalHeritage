package kz.nurkaydarov097.culturalheritage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import kz.nurkaydarov097.culturalheritage.adapters.AcademicAdapter
import kz.nurkaydarov097.culturalheritage.databinding.ActivityMainBinding
import kz.nurkaydarov097.culturalheritage.models.Datasource

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }


    fun initRecyclerView(){
        val dataset = Datasource().loadAcademics()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = AcademicAdapter(this, dataset)
    }
}