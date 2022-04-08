package kz.nurkaydarov097.culturalheritage.models

import kz.nurkaydarov097.culturalheritage.R

class Datasource {
    fun loadAcademics():List<Academic>
    {
        return listOf<Academic>(
            Academic(0, R.string.bukharzhirau_name, R.string.bukharzhirau_desc,R.mipmap.bukharzhirau),
            Academic(1,R.string.isaBaizakov_name, R.string.isaBaizakov_desc, R.mipmap.bayzakov),
            Academic(2,R.string.mashhur_name,R.string.mashhur_desc,R.mipmap.mashhur),
            Academic(3,R.string.toraigyrov_name,R.string.toraigyrov_desc,R.mipmap.toraighyrov),
            Academic(4,R.string.satbayev_name,R.string.satbayev_desc,R.mipmap.satbayev),
            Academic(5,R.string.bekhozhin_name,R.string.bekhozhin_desc,R.mipmap.bekhozhin),
            Academic(6,R.string.aimauytov_name,R.string.aimauytov_desc,R.mipmap.aimautov),
            Academic(7,R.string.bekmakhanov_name,R.string.bekmakhanov_desc,R.mipmap.bekmakhanov),
            Academic(8,R.string.bekturov_name,R.string.bekturov_desc,R.mipmap.bekturov),
            Academic(9,R.string.margulan_name,R.string.margulan_desc,R.mipmap.margulan),
            Academic(10,R.string.shezhire_name,R.string.empty_string,R.mipmap.shezhire),
        )
    }


}