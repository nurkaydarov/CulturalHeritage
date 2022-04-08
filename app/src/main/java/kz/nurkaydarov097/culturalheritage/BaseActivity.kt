package kz.nurkaydarov097.culturalheritage

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import kz.nurkaydarov097.culturalheritage.utils.ContextUtils


open class BaseActivity: AppCompatActivity() {


    override fun attachBaseContext(newBase: Context?) {

        // get chosen language from shread preference
       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        {
            val localeSwitchTo = androidx.preference.PreferenceManager.getDefaultSharedPreferences(newBase)
            val localeUpdatedContext: ContextWrapper? =
                newBase?.let { ContextUtils.updateLocale(it, localeSwitchTo) }
            super.attachBaseContext(localeUpdatedContext)
        }
        else{
            val localeSwitchTo = android.preference.PreferenceManager.getDefaultSharedPreferences(newBase)
            val localeUpdatedContext:ContextWrapper? =
                newBase?.let { ContextUtils.updateLocale(it, localeSwitchTo) }
            super.attachBaseContext(localeUpdatedContext)
        }
*/

    }

}