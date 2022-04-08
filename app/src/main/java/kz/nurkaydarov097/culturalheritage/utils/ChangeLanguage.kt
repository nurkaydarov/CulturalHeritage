package kz.nurkaydarov097.culturalheritage.utils

import android.content.Context
import android.os.Build
import java.util.*

class ChangeLanguage(private val context: Context) {

    fun changeLanguage(lang:String){
        val config = context.resources.configuration
        var language:String = lang
        val locale = Locale(language)
        Locale.setDefault(locale)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            config.setLocale(locale)
        }
        else{
            config.locale = locale
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

}