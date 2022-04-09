package kz.nurkaydarov097.culturalheritage.adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.nurkaydarov097.culturalheritage.ContentActivity
import kz.nurkaydarov097.culturalheritage.MainActivity
import kz.nurkaydarov097.culturalheritage.R
import kz.nurkaydarov097.culturalheritage.models.Academic
import kz.nurkaydarov097.culturalheritage.utils.ChangeLanguage

class AcademicAdapter(val context:Context, private val dataset:List<Academic>, private val langID:String) : RecyclerView.Adapter<AcademicAdapter.AcademicItemViewHolder>()
{
    inner class AcademicItemViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById<ImageView>(R.id.academic_home_image)
        val textViewName: TextView = view.findViewById<TextView>(R.id.home_academic_name)
        val textViewDesc: TextView = view.findViewById(R.id.home_academic_desc)
        val btn_start: Button = view.findViewById(R.id.btn_start)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademicItemViewHolder {
        val adapterLayout =LayoutInflater.from(parent.context).inflate(R.layout.academic_item_list,parent,false)
        return AcademicItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AcademicItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.imageView.setImageResource(item.academicImage)
        holder.textViewName.text = context.resources.getString(item.academicName)
        holder.textViewDesc.text = context.resources.getString(item.academicDesc)

        holder.btn_start.setOnClickListener{
            val intent = Intent(context, ContentActivity::class.java)
            //intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(ID_ACADEMIC, item.id)
            intent.putExtra(LANGUAGE_ID, langID)
            ChangeLanguage(context).changeLanguage(langID)

            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    companion object{
        @JvmStatic val ID_ACADEMIC = "ID_ACADEMIC"
        @JvmStatic val LANGUAGE_ID = "LANGUAGE_ID"
    }
}