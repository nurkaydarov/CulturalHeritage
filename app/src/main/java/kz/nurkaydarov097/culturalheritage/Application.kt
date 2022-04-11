package kz.nurkaydarov097.culturalheritage

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.zeugmasolutions.localehelper.LocaleAwareApplication

class Application : LocaleAwareApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
       // MultiDex.install(this)
    }

}