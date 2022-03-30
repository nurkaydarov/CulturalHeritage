package kz.nurkaydarov097.culturalheritage.models

import androidx.annotation.DrawableRes

data class Academic(
    val id:Int,
    val academicName:Int,
    val academicDesc:Int,
    @DrawableRes val academicImage: Int
    )